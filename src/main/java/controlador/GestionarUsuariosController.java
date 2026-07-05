package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet("/GestionarUsuariosController")
public class GestionarUsuariosController extends HttpServlet {
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
		// Si existe el parámetro 'ruta' lo usamos, si no, por defecto 'listar'
		String ruta = (req.getParameter("ruta") != null) ? req.getParameter("ruta") : "listar";
		switch (ruta) {
		case "listar":
			this.listar(req, resp);
			break;
		case "nuevo":
			this.nuevo(req, resp);
			break;
		case "guardarNuevo":
			this.guardarNuevo(req, resp);
			break;
		case "actualizar":
			this.actualizar(req, resp);
			break;
		case "guardarExistente":
			this.guardarExistente(req, resp);
			break;
		case "eliminar":
			this.eliminar(req, resp);
			break;
		}
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Usuario> personas = Usuario.getUsuarios();
		req.setAttribute("personas", personas);
		req.getRequestDispatcher("jsp/listarusuarios.jsp").forward(req, resp);

	}

	private void nuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("jsp/crearusuario.jsp");

	}

	private void guardarNuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("txtNombre");
		String clave = req.getParameter("txtClave");
		Usuario usuario = new Usuario(0, nombre, clave, false);
		boolean resultado = Usuario.create(usuario);
		if (resultado) {
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		} else {
			req.setAttribute("mensaje", "No se pudo ingresar el usuario nuevo");
			req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
		}

	}

	private void actualizar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idPersona = Integer.parseInt(req.getParameter("idpersona"));
		Usuario persona = Usuario.getUsuarioById(idPersona);
		// Aqui falta verificar que exista la persona, si no existe, redirigir a
		// error.jsp
		req.setAttribute("persona", persona);
		req.getRequestDispatcher("jsp/actualizarusuario.jsp").forward(req, resp);
	}

	private void guardarExistente(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("txtId"));
		String nombre = req.getParameter("txtNombre");
		String clave = req.getParameter("txtClave");
		Usuario usuario = new Usuario(id, nombre, clave, false);

		boolean respuesta = Usuario.update(usuario);

		if (respuesta) {
			// Si la actualización fue exitosa, volver a la lista
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		} else {
			// En caso de error mostrar la página de error con mensaje
			req.setAttribute("mensaje", "Error al actualizar el usuario con id: " + usuario.getId());
			req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
		}
	}

	private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idPersona = Integer.parseInt(req.getParameter("idpersona"));
		try {
			Usuario.delete(idPersona);
			// 3 .- Llamar a la vista
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		} catch (Exception ex) {
			req.setAttribute("mensaje", ex.getMessage());
			req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
		}
	}

}
