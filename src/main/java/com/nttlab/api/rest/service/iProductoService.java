package com.nttlab.api.rest.service;

import java.util.List;

import com.nttlab.api.rest.entity.Producto;

public interface iProductoService {

	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	public Producto findById(Long id);
	
	public List<Producto> findByNombre(String nombre);
	
	public List<Producto> findByCategoria(String categoria);
	
	public Producto findByCodigo(String codigo);
	
	public List<Producto> findByPrecioBetween(Long maxValue, Long minValue);
	
}

