package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String correo;
	private String perfil; 

	private static List<Usuario> usuarios = new ArrayList<>();
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String correo, String perfil) {
		this.nombre = nombre;
		this.correo = correo;
		this.perfil = perfil;
	}

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }
	public String getPerfil() { return perfil; }
	public void setPerfil(String perfil) { this.perfil = perfil; }


	public List<Usuario> getAll() {
		return usuarios;
	}

	public boolean validarcorreo(String correo) {
		for (Usuario u : usuarios) {
			if (u.getCorreo().equals(correo)) {
				return true; 
			}
		}
		return false;
	}

	public void saveuser(String nombre, String correo, String perfil) {
		Usuario nuevoUsuario = new Usuario(nombre, correo, perfil);
		usuarios.add(nuevoUsuario); 
	}

	public void actualizarUsuario(String nombre, String correo, String perfil) {
		for (Usuario u : usuarios) {
			if (u.getCorreo().equals(correo)) {
				u.setNombre(nombre);
				u.setPerfil(perfil);
				break;
			}
		}
	}

	public void eliminarUsuario(String correo) {
		usuarios.removeIf(u -> u.getCorreo().equals(correo));
	}
}