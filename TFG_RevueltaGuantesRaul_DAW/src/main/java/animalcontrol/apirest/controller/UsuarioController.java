package animalcontrol.apirest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import animalcontrol.apirest.entities.Direccion;
import animalcontrol.apirest.entities.Rol;
import animalcontrol.apirest.entities.Tarjeta;
import animalcontrol.apirest.entities.Usuario;
import animalcontrol.apirest.service.DireccionService;
import animalcontrol.apirest.service.RolService;
import animalcontrol.apirest.service.TarjetaService;
import animalcontrol.apirest.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private RolService rserv;
	
	@Autowired
	private UsuarioService userv;
	
	@Autowired
	private DireccionService dserv;
	
	@Autowired
	private TarjetaService tserv;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Mostrar datos personales del Usuario
	@GetMapping("/datos-personales/{id}")
	public String datosUsuario(Model model, @PathVariable(name="id") int idUsuario) {
		Usuario user = userv.findById(idUsuario);
		model.addAttribute("datosUsuario", user);
				
		return "datos-personales";
	}
	
	// Ir a la página de registro
	@GetMapping("/registro")
	public String registrarUsuario(Model model) {
		List<Rol> lista = rserv.todosRoles();
		model.addAttribute("listaRoles", lista);
		
		return "registro";
	}
	
	// Formulario de registro de usuario
	@PostMapping("/registro")
	public String procRegistrarUsuario(Usuario usuario, RedirectAttributes ratt) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEnabled(1);
		
		usuario.setFechaRegistro(new Date());
		
		if(usuario.getRol() == null) {
			usuario.setRol(rserv.buscarRol(2));
			
			if (userv.registro(usuario)) {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Usuario creado correctamente\r\n"
					+ "</div>");
				return "redirect:/index";
			}else {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  Ha ocurrido un error, vuelve a intentarlo\r\n"
					+ "</div>");
				return "/registro";
			}
		}else {
			usuario.setRol(usuario.getRol());
			
			if (userv.registro(usuario)) {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Usuario creado correctamente\r\n"
					+ "</div>");
				return "redirect:/lista-usuarios";
			}else {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  Ha ocurrido un error, vuelve a intentarlo\r\n"
					+ "</div>");
				return "/registro";
			}
		}
	
	}
	
	// Ir al formulario para datos personales del Usuario
	@GetMapping("/editar-perfil/{id}")
	public String editarUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		Usuario user = userv.findById(idUsuario);
		model.addAttribute("datosUsuario", user);
		
		return "editar-perfil";
	}
	
	// Formulario para editar los datos
	@PostMapping("/editar-perfil/{id}")
	public String procesarEdicionUsuario(RedirectAttributes ratt, Usuario usuario, @PathVariable(name="id") int  idUsuario) {
		
		if(userv.findById(idUsuario) == null) {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  El usuario no existe\r\n"
					+ "</div>");
		}else {
			Usuario user = userv.findById(idUsuario);
			usuario.setIdUsuario(user.getIdUsuario());
			usuario.setEnabled(user.getEnabled());
			usuario.setFechaNacimiento(user.getFechaNacimiento());
			usuario.setFechaRegistro(user.getFechaRegistro());
			usuario.setRol(user.getRol());
			usuario.setDirecciones(user.getDirecciones());
			usuario.setTarjetas(user.getTarjetas());
			 if (!usuario.getPassword().equals(user.getPassword())) {
		            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		        }
			
			if (userv.modificarUsuario(usuario) == 1) {
				System.out.println("Usuario modificado con éxito :)");
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  El usuario se ha modificado con éxito\r\n"
					+ "</div>");
			}else {
				System.out.println("Usuario no modificado");
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  El usuario no se ha podido modificar\r\n"
					+ "</div>");
			}
		}
		
		return "redirect:/datos-personales/{id}";
	}
	
	
	// Mostrar lista de direcciones por Usuario
	@GetMapping("/lista-direcciones/{id}")
	public String direccionesUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		List<Direccion> direcciones = dserv.direccionesUsuario(idUsuario);
		Usuario user = userv.findById(idUsuario);
		
		model.addAttribute("usuario", user);
		model.addAttribute("direccionesUsuario", direcciones);
					
		return "lista-direcciones";
	}
	
	// Mostrar lista de tarjetas por Usuario
	@GetMapping("/lista-tarjetas/{id}")
	public String tarjetasUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		List<Tarjeta> tarjetas = tserv.tarjetasUsuario(idUsuario);
		Usuario user = userv.findById(idUsuario);
			
		model.addAttribute("usuario", user);
		model.addAttribute("tarjetasUsuario", tarjetas);
						
		return "lista-tarjetas";
	}
	
	// Formatear la fecha de nacimiento
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	// ADMINISTRADOR
	
	// Pagina para dar de alta roles
	@GetMapping("/alta-roles")
	public String altaRoles() {
		return "alta-roles";
	}
	
	// Formulario para dar de alta roles
	@PostMapping("/alta-roles")
	public String procesarAltaRol(RedirectAttributes ratt, Rol rol) {	
		rserv.insertarRol(rol);
		
		if(rol != null) {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "  Rol creado con éxito\r\n"
				+ "</div>");
		}else {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  El rol no se ha podido crear\r\n"
				+ "</div>");
		}
		return "redirect:/lista-roles";
	}

	// Borrar un rol
	@GetMapping("/borrar-rol/{id}")
	public String eliminar(Model model, @PathVariable(name="id") int  idRol) {	
		if (rserv.borrarRol(idRol) == 1)
			model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "  Rol eliminado con éxito\r\n"
				+ "</div>");
		else
			model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  Rol no eliminado\r\n"
				+ "</div>");
		
		return "forward:/lista-roles";
	}
	
	// Ir al formulario para editar el rol
	@GetMapping("/editar-rol/{id}")
	public String editarRol(Model model, @PathVariable(name="id") int  idRol) {
		Rol rol = rserv.buscarRol(idRol);
		model.addAttribute("rolElegido", rol);
			
		return "editar-rol";
	}
	
	// Formulario para editar el rol
	@PostMapping("/editar-rol/{id}")
	public String procesarCambioRol(RedirectAttributes ratt, Rol rol, @PathVariable(name="id") int  idRol) {
			
		if(rserv.buscarRol(idRol) == null) {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  El rol no existe\r\n"
				+ "</div>");
		}else {
			rol.setIdRol(idRol);
			if (rserv.modificarRol(rol) == 1) {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Rol modificado con éxito\r\n"
					+ "</div>");
			}else {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  Rol no modificado\r\n"
					+ "</div>");
			}
		}
			
		return "redirect:/lista-roles";
	}

	// Mostrar formulario de alta de usuario (solo campos básicos)
	@GetMapping("/alta-usuario")
	public String mostrarFormularioAlta(Model model) {
	    Usuario nuevo = new Usuario();
	    nuevo.setUsername(""); // Opcional, por claridad
	    nuevo.setPassword(""); // Importante si se autocompleta
	    model.addAttribute("usuario", nuevo);
	    return "alta-usuario";
	}

		// Procesar alta de usuario desde formulario básico
		@PostMapping("/alta-usuario")
		public String procesarAltaUsuario(RedirectAttributes ratt, Usuario usuario) {
		    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		    usuario.setFechaRegistro(new Date());
		    usuario.setEnabled(1);
		    usuario.setRol(rserv.buscarRol(2)); // Asignar rol "usuario" por defecto

		    if (userv.registro(usuario)) {
		        ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\">Usuario registrado con éxito</div>");
		        return "redirect:/alta-usuario";
		    } else {
		        ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-danger\">El nombre de usuario ya está en uso</div>");
		        return "redirect:/alta-usuario";
		    }
		}
		

}
