package com.nttlab.api.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="codigo", nullable=false, unique = true)
	private String codigo;
	
	@Column(name="precio", nullable=false)
	private Long precio;

	@Column(name="imagen", nullable=false)
	private String imagen;
	
	@Size(min = 2, max = 50)
	@Column(name="categoria", nullable=false)
	private String categoria;
	
	@Column(name="stock", nullable=false)
	private int stock;
	
	@Size(min = 2, max = 50)
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Size(min = 2, max = 300)
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	public Producto() {}

	public Producto(Long id, Long precio, String imagen, String nombre, int stock, String categoria,
			String descripcion) {
		this.id = id;
		this.precio = precio;
		this.imagen = imagen;
		this.nombre = nombre;
		this.stock = stock;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}
	
	public Producto(Long precio, String imagen, String nombre, int stock, String categoria,
			String descripcion) {
		this.precio = precio;
		this.imagen = imagen;
		this.nombre = nombre;
		this.stock = stock;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private static final long serialVersionUID = 1L;
	
}
