package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String clave;
	private boolean isAdmin;

	private static List<Usuario> usuarios = null;

	public Usuario() {
	}

	public Usuario(int id, String nombre, String clave, boolean isAdmin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/*------------------------------------*/

	public static Usuario authenticate(String username, String password) {

		Usuario usuarioAutorizado = null;
		List<Usuario> usuarios = getUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(username) && usuario.getClave().equals(password)) {

				usuarioAutorizado = usuario;
				break;
			}
		}

		return usuarioAutorizado;
	}

	public static List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
			usuarios.add(new Usuario(1, "admin", "admin", true));
			usuarios.add(new Usuario(2, "user", "user", false));
		}
		return usuarios;
	}

	public static Usuario getUsuarioById(int idUsuario) {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getId() == idUsuario) {
				return usuario;
			}
		}
		return null;
	}

	public static boolean create(Usuario usuario) {
		int max = 0;

		for (Usuario u : getUsuarios()) {
			if (u.getId() > max) {
				max = u.getId();
			}
		}

		max = max + 1;
		usuario.setId(max);
		getUsuarios().add(usuario);

		return true;
	}

	public static boolean update(Usuario usuario) {
		List<Usuario> listaUsuarios = getUsuarios();

		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getId() == usuario.getId()) {
				listaUsuarios.get(i).setClave(usuario.getClave());
				listaUsuarios.get(i).setNombre(usuario.getNombre());

				break;
			}
		}

		return true;
	}

	public static boolean delete(int idUsuario) throws Exception {
		Usuario usuarioEncontrado = getUsuarioById(idUsuario);
		if (usuarioEncontrado.isAdmin) {
			throw new Exception("Lo siento: Un administrador no puede ser eliminado");
		} else {
			int indice = 0;
			List<Usuario> listaUsuarios = getUsuarios();
			for (int i = 0; i < listaUsuarios.size(); i++) {
				if (listaUsuarios.get(i).getId() == idUsuario) {
					indice = i;
					break;
				}
			}

			listaUsuarios.remove(indice);

		}

		return true;

	}

}