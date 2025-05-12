package animalcontrol.apirest.service;

import java.util.List;

import animalcontrol.apirest.entities.Usuario;

public interface UsuarioService {
	
	// Lista de todos los usuarios
	List<Usuario> todosUsuarios();
	
	// Buscar un usuario por su ID
	Usuario findById(int idUsuario);
	
	// Buscar un usuario por su nombre
	Usuario buscarUsuario(String username);
	
	// Registrar un usuario 
	boolean registro(Usuario usuario);
	
	// Modificar un usuario
	int modificarUsuario(Usuario usuario);
	
	
}
