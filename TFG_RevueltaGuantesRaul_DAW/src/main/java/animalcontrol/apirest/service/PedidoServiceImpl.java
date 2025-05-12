package animalcontrol.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import animalcontrol.apirest.entities.Pedido;
import animalcontrol.apirest.entities.Usuario;
import animalcontrol.apirest.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	private PedidoRepository prepo;

	// Sacar lista de pedidos de un usuario
	@Override
	public List<Pedido> pedidosUsuario(int idUsuario) {
		return prepo.buscarPorId(idUsuario);
	}
	
	
	// Dar de alta un pedido
		@Override
		public int altaPedido(Pedido pedido, String estado) {
			
			pedido.setEstado(estado);
			
			if (prepo.save(pedido) != null) {
				System.out.println(pedido);
				return 1;
			}else {
				return 0;
			}
			
		}

	// Buscar un pedido por su ID
	@Override
	public Pedido findById(int idPedido) {
		return prepo.findById(idPedido).orElse(null);
	}
}
