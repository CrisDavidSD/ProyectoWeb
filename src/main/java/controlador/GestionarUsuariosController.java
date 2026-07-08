package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.UsuarioDAOImpl;

@WebServlet("/GestionarUsuariosController")
public class GestionarUsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

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

		List<Usuario> personas = usuarioDAO.obtenerTodos();
		req.setAttribute("personas", personas);
		req.getRequestDispatcher("/jsp/listarusuarios.jsp").forward(req, resp);
	}

	private void nuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/jsp/crearusuario.jsp");

	}

	private void guardarNuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("Nombre");
		String clave = req.getParameter("Clave");
		String correo = req.getParameter("Correo");
		String rolParam = req.getParameter("Rol");
		boolean rol = (rolParam != null); 
		
		Usuario usuario = new Usuario(0, nombre, clave, rol, correo);
		boolean resultado = usuarioDAO.insertar(usuario);
		if (resultado) {
			resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=listar");
		} else {
			req.getSession().setAttribute("mensajeError", "Ya existe un usuario con el mismo correo.");
			resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=nuevo");
		}
	}

	private void actualizar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idPersona = Integer.parseInt(req.getParameter("idpersona"));
		Usuario persona = usuarioDAO.buscarPorId(idPersona);
		if (persona == null) {
			req.setAttribute("mensajeError", "Persona no encontrada");
			req.getRequestDispatcher("/jsp/listarusuarios.jsp").forward(req, resp);
		} else {
			req.setAttribute("persona", persona);
			req.getRequestDispatcher("/jsp/actualizarusuario.jsp").forward(req, resp);
		}
	}

	private void guardarExistente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    int id = Integer.parseInt(req.getParameter("Id"));
	    String nombre = req.getParameter("Nombre");
	    String clave = req.getParameter("Clave");
	    String correo = req.getParameter("Correo"); 
	    
	    String rolParam = req.getParameter("Rol");  
	    boolean rol = (rolParam != null); 
	    
	    Usuario usuario = new Usuario(id, nombre, clave, rol, correo);

	    try {
	        boolean respuesta = usuarioDAO.actualizar(usuario); 

	        if (respuesta) {
	            resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=listar");
	        }
	        
	    } catch (Exception e) {
	        if (e.getMessage().equals("CORREO_REPETIDO")) {
	            req.getSession().setAttribute("mensajeError", "El correo está repetido.");
	        } else if (e.getMessage().equals("ES_ADMINISTRADOR")) {
	            req.getSession().setAttribute("mensajeError", "No puedes quitarle el rol a un Administrador.");
	        }

	        resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=actualizar&idpersona=" + id);
	    }
	}

	private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    int idUsuario = Integer.parseInt(req.getParameter("idpersona"));
	    
	    try {
	    	
	    	boolean respuesta = usuarioDAO.eliminar(idUsuario);
	        
	    	if(respuesta) {
	    		resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=listar");
	    	}
	        
	    } catch (Exception ex) {

	        if (ex.getMessage().equals("USUARIO_NO_ENCONTRADO")) {
	            req.getSession().setAttribute("mensajeError", "Error: El usuario que intentas eliminar no existe o ya fue borrado.");
	        } else if (ex.getMessage().equals("ES_ADMINISTRADOR")) {
	            req.getSession().setAttribute("mensajeError", "Lo siento: Un administrador no puede ser eliminado.");
	        } 

	        resp.sendRedirect(req.getContextPath() + "/GestionarUsuariosController?ruta=listar");
	    }
	}

}
