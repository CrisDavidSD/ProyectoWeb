package recursos;

import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Usuario;
import modelo.UsuarioDAOImpl;

@Path("/usuarios")
public class RecursoUsuario {

	private UsuarioDAOImpl dao;

	public RecursoUsuario() {
		dao = new modelo.UsuarioDAOImpl();
	}

	@Path("/listar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios() {
		return Response.ok(dao.obtenerTodos()).build();
	}

	@Path("/obtener/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("id") int id) {
		Usuario user = dao.buscarPorId(id);
		if (user != null) {
			return Response.ok(user).build();
		}

		return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Usuario no encontrado\"}").build();
	}

	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardarUsuario(Usuario usuario) {
		boolean exito = dao.insertar(usuario);
		if (exito) {
			return Response.status(Response.Status.CREATED).entity(usuario).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"El correo ya existe\"}").build();
		}
	}

	@Path("/update/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
		try {
			usuario.setId(id);

			boolean exito = dao.actualizar(usuario);
			if (exito) {
				return Response.ok(usuario).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Usuario no encontrado\"}")
						.build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"" + e.getMessage() + "\"}")
					.build();
		}
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarUsuario(@PathParam("id") int id) {
		try {
			boolean exito = dao.eliminar(id);
			if (exito) {
				return Response.ok().entity("{\"mensaje\":\"Usuario eliminado correctamente\"}").build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"" + e.getMessage() + "\"}")
					.build();
		}
	}
}