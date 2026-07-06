package modelo;

import java.util.List;

public interface UsuarioDAO {

	boolean insertar(Usuario usuario);

	boolean actualizar(Usuario usuario) throws Exception;

	boolean eliminar(int id) throws Exception;

	Usuario buscarPorId(int id);

	List<Usuario> obtenerTodos();

	Usuario autenticar(String nombre, String clave);
}
