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
public class GestionarControlador extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ingresar() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		List <Usuario> listaUsuarios = usuario.getAll(); 
		
		req.setAttribute("usuarios", listaUsuarios);
		req.getRequestDispatcher("vista/ListaUsuarios.jsp");
		

	}

	private void entrar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		

		resp.sendRedirect("vista/login.jsp");

	}

	public void ruteador(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String ruta = req.getParameter("ruta");
		switch (ruta) {

		case "ingresar":
			this.ingresar(req, resp);
			break;
		}

	}
	
}
