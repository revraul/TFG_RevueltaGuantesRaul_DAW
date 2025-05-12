package animalcontrol.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import animalcontrol.apirest.entities.Direccion;
import animalcontrol.apirest.entities.Tarjeta;
import animalcontrol.apirest.entities.Usuario;
import animalcontrol.apirest.repository.UsuarioRepository;
import animalcontrol.apirest.service.DireccionService;
import animalcontrol.apirest.service.UsuarioService;

@Controller
public class DireccionesController {
	
	@Autowired
	private DireccionService dserv;

	@Autowired
	private UsuarioService userv;
	
	@Autowired
	private UsuarioRepository urepo;
	
	
	
	// Ir al formulario para añadir direccion del Usuario
	@GetMapping("/alta-direccion/{id}")
	public String altaDireccion(Model model, @PathVariable(name="id") int  idUsuario) {
		Usuario user = userv.findById(idUsuario);
		model.addAttribute("usuario", user);
				
		return "alta-direccion";
	}
		
	// Formulario para añadir direccion del Usuario
	@PostMapping("/alta-direccion/{id}")
	public String altaDireccion(RedirectAttributes ratt, Direccion direccion, @PathVariable(name="id") int  idUsuario) {
		Usuario user = userv.findById(idUsuario);
			
		user.addDireccion(direccion);
		urepo.save(user);
			
		ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "  Direccion añadida correctamente\r\n"
				+ "</div>");
			
		return "redirect:/lista-direcciones/{id}";
	}
	
	// Borrar direreccion del usuario
	@GetMapping("/borrar-direccion/{id}")
	public String eliminarDireccion(Authentication aut, Model model, @PathVariable("id") int idDireccion) {
		String username = aut.getName();
		Usuario user = userv.buscarUsuario(username);
			
		Direccion direccion = dserv.buscarDireccion(idDireccion);
			
		user.removeDireccion(direccion);
		urepo.save(user);
			
		model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "  Direccion eliminada correctamente\r\n"
				+ "</div>");
		
		List<Direccion> direcciones = dserv.direccionesUsuario(user.getIdUsuario());
		model.addAttribute("direccionesUsuario", direcciones);
			
		return "lista-direcciones";
	}
	
	// Ir al formulario para editar la direccion
	@GetMapping("/editar-direccion/{id}")
	public String editarTarjeta(Model model, @PathVariable(name="id") int  idDireccion) {
		Direccion direccion = dserv.buscarDireccion(idDireccion);
		model.addAttribute("direccionElegida", direccion);
			
		return "editar-direccion";
	}
	
	// Formulario para editar la direccion
	@PostMapping("/editar-direccion/{id}")
	public String procesarCambioTarjeta(RedirectAttributes ratt, Direccion direccion, @PathVariable(name="id") int  idDireccion) {
			
		if(dserv.buscarDireccion(idDireccion) == null) {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  La direccion no existe\r\n"
				+ "</div>");
		}else {
			direccion.setIdDireccion(idDireccion);
			if (dserv.modificarDireccion(direccion) == 1) {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Dirección modificada con éxito\r\n"
					+ "</div>");
			}else {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  Dirección no modificada\r\n"
					+ "</div>");
			}
		}
			
		return "redirect:/";
	}	
}
