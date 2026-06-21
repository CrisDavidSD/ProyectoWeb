package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet("/gestionarControlador")
public class GestionarControlador extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.routeador(req, resp); // Nombrado 'routeador' según el DSD
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.routeador(req, resp); // Nombrado 'routeador' según el DSD
	}

	// Fiel al nombre del método en el Diagrama de Secuencia (DSD)
	public void routeador(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String ruta = req.getParameter("ruta");
		if (ruta == null) ruta = "ingresar"; // Valor por defecto

		switch (ruta) {
			case "ingresar":
				this.ingresar(req, resp);
				break;
			case "newUser": // Fiel al DSD
				this.newUser(req, resp);
				break;
			case "crearUsuario": // Fiel al DSD y Robustez
				this.crearUsuario(req, resp);
				break;
			case "update": // Fiel a Robustez
				this.update(req, resp);
				break;
			case "actualizarUsuario": // Fiel a Robustez
				this.actualizarUsuario(req, resp);
				break;
			case "delete": // Fiel a Robustez
				this.delete(req, resp);
				break;
			case "eliminarUsuario": // Fiel a Robustez
				this.eliminarUsuario(req, resp);
				break;
			default:
				this.ingresar(req, resp);
				break;
		}
	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		// DSD: getAll(): List<Usuarios>
		List<Usuario> listaUsuarios = usuario.getAll(); 
		
		req.setAttribute("usuarios", listaUsuarios);
		// DSD: mostrar(usuarios: List<Usuarios>) en ListaUsuarios
		req.getRequestDispatcher("vista/ListaUsuarios.jsp").forward(req, resp);
	}

	// Fiel a "newUser()" en DSD y "newuser" en Robustez
	private void newUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DSD: presentar() en NuevoUsuario
		req.getRequestDispatcher("vista/NuevoUsuario.jsp").forward(req, resp);
	}

	// Fiel a "crearUsuario(nombre, correo, perfil)"
	private void crearUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String correo = req.getParameter("correo");
		String rol = req.getParameter("rol"); // El DSD especifica parámetro 'rol: String'

		Usuario usuarioModel = new Usuario();
		
		// DSD: validarcorreo(correo)
		boolean duplicado = usuarioModel.validarcorreo(correo);

		if (duplicado) {
			// DSD [ALT: correo duplicado]: showfail("Ya existe un usuario con ese correo") 
			// En el DSD la flecha apunta de regreso a la vista NuevoUsuario
			req.setAttribute("mensajeError", "Ya existe un usuario con ese correo");
			req.getRequestDispatcher("vista/NuevoUsuario.jsp").forward(req, resp);
		} else {
			// DSD [ALT: correo valido]: saveuser(nombre, correo, rol)
			usuarioModel.saveuser(nombre, correo, rol);
			
			// DSD: presentarListaActualizada()
			resp.sendRedirect("gestionarControlador?ruta=ingresar");
		}
	}

	// Fiel a "update" en Robustez
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("vista/ActualizarUsuario.jsp").forward(req, resp);
	}

	// Fiel a "actualizarUsuario(nombre, correo, perfil)" en Robustez
	private void actualizarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String correo = req.getParameter("correo");
		String perfil = req.getParameter("perfil");

		Usuario usuarioModel = new Usuario();
		usuarioModel.actualizarUsuario(nombre, correo, perfil);

		resp.sendRedirect("gestionarControlador?ruta=ingresar");
	}

	// Fiel a "delete" en Robustez
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("vista/EliminarUsuario.jsp").forward(req, resp);
	}

	// Fiel a "eliminarUsuario(correo)" en Robustez
	private void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String correo = req.getParameter("correo");

		Usuario usuarioModel = new Usuario();
		usuarioModel.eliminarUsuario(correo);

		resp.sendRedirect("gestionarControlador?ruta=ingresar");
	}

	private void entrar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("vista/login.jsp");
	}
}