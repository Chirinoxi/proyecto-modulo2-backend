package com.nttlab.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.api.rest.dao.iProductoDAO;
import com.nttlab.api.rest.entity.Producto;

@Service
public class ProductoService implements iProductoService {
	
	@Autowired
	private iProductoDAO productoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>) this.productoDao.findAll();
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return this.productoDao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.productoDao.deleteById(id);	
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		Optional<Producto> result =  this.productoDao.findById(id);
		if(result.isEmpty()) {
			return null;
		}
		Producto producto = result.get();
		return producto;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findByNombre(String nombre) {
		return this.productoDao.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findByCategoria(String categoria) {
		return this.productoDao.findByCategoria(categoria);
	}

	@Override
	public Producto findByCodigo(String codigo) {
		return this.productoDao.findByCodigo(codigo);
	}

	@Override
	public List<Producto> findByPrecioBetween(Long maxValue, Long minValue) {
		return this.productoDao.findByPrecioBetween(maxValue, minValue);
	}

}
