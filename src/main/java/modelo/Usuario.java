package modelo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String correo;
	private String perfi;

	private List<Usuario> usuarios;
	
	public Usuario() {
	}
	
	

	public Usuario(String nombre, String correo, String perfi) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.perfi = perfi;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfi() {
		return perfi;
	}

	public void setPerfi(String perfi) {
		this.perfi = perfi;
	}


	public List<Usuario> getAll() {
		return usuarios;
	}
}
