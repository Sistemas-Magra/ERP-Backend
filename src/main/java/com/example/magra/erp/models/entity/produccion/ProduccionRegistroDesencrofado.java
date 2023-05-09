package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_poste_registro_desencrofado")
public class ProduccionRegistroDesencrofado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajo ordenTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;

	@Column(length=200)
	private String responsable;
	
	private String inconformidadProduccion;
	
	private Boolean indConforme;
	
	@Column(length=400)
	private String observacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public OrdenTrabajoDetalle getOrdenTrabajoDetalle() {
		return ordenTrabajoDetalle;
	}

	public void setOrdenTrabajoDetalle(OrdenTrabajoDetalle ordenTrabajoDetalle) {
		this.ordenTrabajoDetalle = ordenTrabajoDetalle;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getInconformidadProduccion() {
		return inconformidadProduccion;
	}

	public void setInconformidadProduccion(String inconformidadProduccion) {
		this.inconformidadProduccion = inconformidadProduccion;
	}

	public Boolean getIndConforme() {
		return indConforme;
	}

	public void setIndConforme(Boolean indConforme) {
		this.indConforme = indConforme;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	private static final long serialVersionUID = 1L;

}