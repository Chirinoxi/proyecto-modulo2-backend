package com.nttlab.api.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttlab.api.rest.entity.Producto;

@Repository
public interface iProductoDAO extends CrudRepository<Producto, Long>{
	
	public List<Producto> findByNombre(String nombre);
	public List<Producto> findByCategoria(String categoria);
	public Producto findByCodigo(String codigo);
	
	@Query("SELECT p FROM Producto p WHERE p.precio BETWEEN ?1 AND ?2")
	public List<Producto> findByPrecioBetween(Long minValue, Long maxValue);

}
