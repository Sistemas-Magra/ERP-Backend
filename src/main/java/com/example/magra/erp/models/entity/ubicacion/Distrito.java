package com.example.magra.erp.models.entity.ubicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ubi_distrito")
public class Distrito implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String nombre;
	
	@Column(length=10)
	private String ubigeo;
	
	@Column(length=10)
	private String ubigeoSunat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	public String getUbigeoSunat() {
		return ubigeoSunat;
	}

	public void setUbigeoSunat(String ubigeoSunat) {
		this.ubigeoSunat = ubigeoSunat;
	}

	private static final long serialVersionUID = 1L;
}
