package modelo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionUsuariosPU");

	@Override
	public boolean insertar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
			query.setParameter("correo", usuario.getCorreo());

			if (!query.getResultList().isEmpty()) {
				return false;
			}

			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();

			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean actualizar(Usuario usuarioActualizado) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u WHERE u.correo = :correo AND u.id <> :id", Usuario.class);
			query.setParameter("correo", usuarioActualizado.getCorreo());
			query.setParameter("id", usuarioActualizado.getId());

			if (!query.getResultList().isEmpty()) {
				throw new Exception("CORREO_REPETIDO");
			}

			Usuario usuarioEnBd = em.find(Usuario.class, usuarioActualizado.getId());

			if (usuarioEnBd == null) {
				return false;
			}

			if (usuarioEnBd.isAdmin() && !usuarioActualizado.isAdmin()) {
				throw new Exception("ES_ADMINISTRADOR");
			}

			em.getTransaction().begin();
			usuarioEnBd.setNombre(usuarioActualizado.getNombre());
			usuarioEnBd.setClave(usuarioActualizado.getClave());
			usuarioEnBd.setCorreo(usuarioActualizado.getCorreo());
			usuarioEnBd.setAdmin(usuarioActualizado.isAdmin());
			em.getTransaction().commit();

			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean eliminar(int id) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);

			if (usuario == null) {
				throw new Exception("USUARIO_NO_ENCONTRADO");
			}

			if (usuario.isAdmin()) {
				throw new Exception("ES_ADMINISTRADOR");
			}

			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();

			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public Usuario buscarPorId(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Usuario> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Usuario autenticar(String nombre, String clave) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.clave = :clave", Usuario.class);
			query.setParameter("nombre", nombre);
			query.setParameter("clave", clave);

			List<Usuario> resultado = query.getResultList();
			return resultado.isEmpty() ? null : resultado.get(0);
		} finally {
			em.close();
		}
	}
}
