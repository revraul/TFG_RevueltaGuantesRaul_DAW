package animalcontrol.apirest.service;

import java.math.BigDecimal;
import java.util.List;

import animalcontrol.apirest.entities.Producto;

public interface ProductoService {

	// Lista de todos los productos
	List<Producto> todosProductos();
	
	// Buscar un producto por su ID
	Producto buscarUno(int idProducto);
	
	// Lista de los productos segun su tamaño
	List<Producto> findByTamano(String tamano);
	
	// Ordenación de productos
	List<Producto> OrderByPriceAsc();
	List<Producto> OrderByPriceDesc();

	// Borrar un producto
	int borrarProducto(int idProducto);
	
	// Insertar un producto
	int insertarProducto(Producto producto);
	
	// Modificar un producto
	int modificarProducto(Producto producto);
		
	// Listad de productos que coincidan con lo que se busca por el nombre
	List<Producto>buscador(String nombre);
	

	// API REST
	Producto buscadorNombre(String nombre);
	BigDecimal findPrecioByNombre(String nombre);
	
}
