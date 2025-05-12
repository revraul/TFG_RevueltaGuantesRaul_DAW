package animalcontrol.apirest.service;

import java.util.List;

import animalcontrol.apirest.entities.Tarjeta;

public interface TarjetaService {

	// Sacar el listado de tarjetas
	List<Tarjeta> todasTarjetas();
	
	// Sacar el listado de tarjetas de un usuario
	List<Tarjeta> tarjetasUsuario(int idUsuario);
	
	// Modificar la tarjetas que te pasan
	int modificarTarjeta(Tarjeta tarjeta);
	
	// Buscar la tarjetas por su ID
	Tarjeta buscarTarjeta(int idTarjeta);
}
