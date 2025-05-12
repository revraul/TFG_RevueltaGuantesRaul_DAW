package animalcontrol.apirest.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import animalcontrol.apirest.entities.Tamano;
import animalcontrol.apirest.repository.TamanoRepository;

@Service
public class TamanoServiceImpl implements TamanoService{

	@Autowired
	private TamanoRepository trepo;

	// Sacar lista de tamanos
	@Override
	public List<Tamano> todosTamanos() {
		return trepo.findAll();
	}

	// Buscar una familia por el ID
	@Override
	public Tamano buscarUno(int idTamano) {
		return trepo.findById(idTamano).orElse(null);
	}

}
