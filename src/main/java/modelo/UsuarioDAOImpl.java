package modelo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager em = Persistence.createEntityManagerFactory("GestionUsuariosPU").createEntityManager();

	@Override
	public boolean insertar(Usuario usuario) {

		// Misma regla que antes: no se puede repetir el correo
		TypedQuery<Usuario> query = em.createQuery(
				"SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
		query.setParameter("correo", usuario.getCorreo());

		if (!query.getResultList().isEmpty()) {
			return false; // El correo ya existe, no se puede crear el usuario
		}

		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();

		return true;
	}

	@Override
	public boolean actualizar(Usuario usuarioActualizado) throws Exception {

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
	}

	@Override
	public boolean eliminar(int id) throws Exception {

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
	}

	@Override
	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> obtenerTodos() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}

	@Override
	public Usuario autenticar(String nombre, String clave) {
		TypedQuery<Usuario> query = em.createQuery(
				"SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.clave = :clave", Usuario.class);
		query.setParameter("nombre", nombre);
		query.setParameter("clave", clave);

		List<Usuario> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
}
