package animalcontrol.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import animalcontrol.apirest.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	// Selecciona los Pedidos del cliente que le pasa por ID
	@Query("select p from Pedido p where p.usuario.idUsuario =?1")
	public List<Pedido> buscarPorId(int idUsuario);
}
