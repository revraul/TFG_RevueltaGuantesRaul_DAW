package animalcontrol.apirest.service;

import java.util.List;

import animalcontrol.apirest.entities.Tamano;

public interface TamanoService {
	// SAcar lista de tamanos
	List<Tamano> todosTamanos();
			
	// Buscar una tamano por el ID
	Tamano buscarUno(int idTamano);
}
