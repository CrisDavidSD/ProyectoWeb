package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String correo;
	private String perfil; // Corregido de 'perfi' a 'perfil' según diagramas

	// Lista estática para simular la base de datos y que los flujos funcionen
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

	// --- Métodos fieles al DSD (Diagrama de Secuencia de Diseño) ---

	public List<Usuario> getAll() {
		return usuarios;
	}

	public boolean validarcorreo(String correo) {
		for (Usuario u : usuarios) {
			if (u.getCorreo().equals(correo)) {
				return true; // Simula el retorno duplicado() del DSD
			}
		}
		return false;
	}

	// El DSD especifica los parámetros (nombre: String, correo: String, rol: String)
	public void saveuser(String nombre, String correo, String rol) {
		Usuario nuevoUsuario = new Usuario(nombre, correo, rol);
		usuarios.add(nuevoUsuario); // Simula el retorno usuarioguardado() del DSD
	}

	// --- Métodos fieles al Diagrama de Robustez (CRUD faltante en DSD) ---

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