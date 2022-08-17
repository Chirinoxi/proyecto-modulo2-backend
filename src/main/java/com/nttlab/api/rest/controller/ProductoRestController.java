package com.nttlab.api.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttlab.api.rest.entity.Producto;
import com.nttlab.api.rest.service.iProductoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*", allowedHeaders = "*") // CORS Cross Origin Resource Sharing
public class ProductoRestController {
	
	@Autowired
	private iProductoService productoService;
	
	
	@GetMapping(value="/productos/lista", produces="application/json")
	public ResponseEntity<?> showAll(){
		List<Producto> productos = null;
		Map<String, Object> response = new HashMap();
		try {
			productos = this.productoService.findAll();
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productos.size() == 0) {
			response.put("mensaje", "¡No hay productos registrados en la base de datos!");
			response.put("productos", productos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Actualmente la base de datos cuenta con: " + productos.size() + " registros.");
		response.put("productos", productos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/productos/{id}", produces="application/json")
	public ResponseEntity<?> showById(@PathVariable("id") Long id){
		Producto producto = null;
		Map<String, Object> response = new HashMap();
		try {
			producto = this.productoService.findById(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (producto == null) {
			response.put("mensaje", "¡Este producto NO se encuentra registrado en nuestra base de datos!");
			response.put("producto", producto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El producto encontrado es: " + producto.toString());
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/productos/categoria/{nombreCategoria}", produces="application/json")
	public ResponseEntity<?> showByCategoria(@PathVariable("nombreCategoria") String categoria){
		List<Producto> productos = null;
		Map<String, Object> response = new HashMap();
		try {
			productos = this.productoService.findByCategoria(categoria);
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productos.size() == 0) {
			response.put("mensaje", "¡No existen productos registrados para esta categoría!");
			response.put("producto", productos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Actualmente la base de datos cuenta con: " + productos.size() + " registros.");
		response.put("producto", productos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/productos/rango", produces="application/json")
	public ResponseEntity<?> showByRangeOfPrices(@RequestParam("precioMin") Long precioMin, @RequestParam("precioMax") Long precioMax){
		List<Producto> productos = null;
		Map<String, Object> response = new HashMap();
		try {
			productos = this.productoService.findByPrecioBetween(precioMin, precioMax);
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productos.size() == 0) {
			response.put("mensaje", "¡No existen productos dentro del rango de precio ingresado!");
			response.put("producto", productos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Actualmente la base de datos cuenta con: " + productos.size() + " registros que cumplen la condición.");
		response.put("producto", productos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/productos/nombre/{nombreProducto}", produces="application/json")
	public ResponseEntity<?> showByNombre(@PathVariable("nombreProducto") String nombre){
		List<Producto> productos = null;
		Map<String, Object> response = new HashMap();
		try {
			productos = this.productoService.findByNombre(nombre);
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productos.size() == 0) {
			response.put("mensaje", "¡No existen productos registrados con este nombre!");
			response.put("producto", productos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Actualmente la base de datos cuenta con: " + productos.size() + " registros.");
		response.put("producto", productos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// TODO: implementar .....
	@PostMapping(value="/productos", produces="application/json")
	public ResponseEntity<?> registrarProducto(@RequestBody Producto producto){
		Map<String, Object> response = new HashMap();
		Producto productoBuscado = null;
		try {
			productoBuscado = this.productoService.findByCodigo(producto.getCodigo());
			if(productoBuscado == null) {
				this.productoService.save(producto);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productoBuscado != null) {
			response.put("mensaje", "¡El producto ingresado ya se encuentra registrado!");
			response.put("producto", producto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ALREADY_REPORTED);
		}
		response.put("mensaje", "!Producto ingresado con éxito! ");
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PatchMapping(value="/productos/{id}", produces="application/json")
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id){
		Map<String, Object> response = new HashMap();
		Producto productoBuscado = null;
		Producto productoActualizado = null;
		
		try {
			productoBuscado = this.productoService.findById(id);
			if(productoBuscado != null) {
				productoBuscado.setNombre(producto.getNombre());
				productoBuscado.setStock(producto.getStock());
				productoBuscado.setPrecio(producto.getPrecio());
				productoBuscado.setDescripcion(producto.getDescripcion());
				productoBuscado.setCategoria(producto.getCategoria());
				productoBuscado.setImagen(producto.getImagen());
				productoActualizado = this.productoService.save(productoBuscado);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productoBuscado == null) {
			response.put("mensaje", "¡El producto ingresado no existe!");
			response.put("producto", producto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "!Producto actualizado con éxito! ");
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/productos/{id}", produces="application/json")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
		Map<String, Object> response = new HashMap();
		Producto productoBuscado = null;
		try {
			productoBuscado = this.productoService.findById(id);
			if(productoBuscado != null) {
				this.productoService.delete(id);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "¡Error al realizar la consulta!");
			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productoBuscado == null) {
			response.put("mensaje", "¡El producto ingresado no existe!");
			response.put("producto", productoBuscado);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "!Producto eliminado con éxito! ");
		response.put("producto", productoBuscado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
