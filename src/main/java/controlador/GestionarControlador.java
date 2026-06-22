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
		this.routeador(req, resp); 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.routeador(req, resp); 
	}

	public void routeador(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String ruta = req.getParameter("ruta");
		if (ruta == null) ruta = "ingresar"; 

		switch (ruta) {
			case "ingresar":
				this.ingresar(req, resp);
				break;
			case "newUser": 
				this.newUser(req, resp);
				break;
			case "crearUsuario": 
				this.crearUsuario(req, resp);
				break;
			case "update": 
				this.update(req, resp);
				break;
			case "actualizarUsuario": 
				this.actualizarUsuario(req, resp);
				break;
			case "delete": 
				this.delete(req, resp);
				break;
			case "eliminarUsuario": 
				this.eliminarUsuario(req, resp);
				break;
			default:
				this.ingresar(req, resp);
				break;
		}
	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = new Usuario();

		List<Usuario> listaUsuarios = usuario.getAll(); 
		
		req.setAttribute("usuarios", listaUsuarios);

		req.getRequestDispatcher("vista/ListaUsuarios.jsp").forward(req, resp);
	}


	private void newUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("vista/NuevoUsuario.jsp").forward(req, resp);
	}


	private void crearUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String correo = req.getParameter("correo");
		String perfil = req.getParameter("perfil"); 

		Usuario usuarioModel = new Usuario();
		

		boolean duplicado = usuarioModel.validarcorreo(correo);

		if (duplicado) {
			req.setAttribute("mensajeError", "Ya existe un usuario con ese correo");
			req.getRequestDispatcher("vista/NuevoUsuario.jsp").forward(req, resp);
		} else {

			usuarioModel.saveuser(nombre, correo, perfil);
			
			resp.sendRedirect("gestionarControlador?ruta=ingresar");
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("vista/ActualizarUsuario.jsp").forward(req, resp);
	}

	private void actualizarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String correo = req.getParameter("correo");
		String perfil = req.getParameter("perfil");

		Usuario usuarioModel = new Usuario();
		usuarioModel.actualizarUsuario(nombre, correo, perfil);

		resp.sendRedirect("gestionarControlador?ruta=ingresar");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("vista/EliminarUsuario.jsp").forward(req, resp);
	}

	private void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String correo = req.getParameter("correo");

		Usuario usuarioModel = new Usuario();
		usuarioModel.eliminarUsuario(correo);

		resp.sendRedirect("gestionarControlador?ruta=ingresar");
	}
}