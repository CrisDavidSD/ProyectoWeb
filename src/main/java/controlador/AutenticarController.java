package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

@WebServlet("/AutenticarController")
public class AutenticarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ruta = (req.getParameter("ruta") == null) ? "iniciar" : req.getParameter("ruta");
		switch (ruta) {
		case "ingresar":
			this.ingresar(req, resp);
			break;
		case "iniciar":
			this.iniciar(req, resp);
			break;
		}
	}

	private void iniciar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("jsp/login.jsp");

	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuario = req.getParameter("usuario");
		String clave = req.getParameter("clave");

		Usuario resultado = Usuario.authenticate(usuario, clave);

		if (resultado == null) {
			resp.sendRedirect("jsp/login.jsp");
		} else {
			HttpSession sesionSitio = req.getSession();
			sesionSitio.setAttribute("autorizado", resultado);
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		}

	}

}
