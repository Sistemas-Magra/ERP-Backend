package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_registro_tubos_pines")
public class ProduccionRegistroTubosPines implements Serializable {

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle medidaDiametroPines;

	@Column(precision=10, scale=2)
	private BigDecimal longitudPines;
	
	private Integer cantidadPines;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle medidaDiametroPistones;

	@Column(precision=10, scale=2)
	private BigDecimal longitudPistones;
	
	private Integer cantidadPistones;
	
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

	public TablaAuxiliarDetalle getMedidaDiametroPines() {
		return medidaDiametroPines;
	}

	public void setMedidaDiametroPines(TablaAuxiliarDetalle medidaDiametroPines) {
		this.medidaDiametroPines = medidaDiametroPines;
	}

	public BigDecimal getLongitudPines() {
		return longitudPines;
	}

	public void setLongitudPines(BigDecimal longitudPines) {
		this.longitudPines = longitudPines;
	}

	public Integer getCantidadPines() {
		return cantidadPines;
	}

	public void setCantidadPines(Integer cantidadPines) {
		this.cantidadPines = cantidadPines;
	}

	public TablaAuxiliarDetalle getMedidaDiametroPistones() {
		return medidaDiametroPistones;
	}

	public void setMedidaDiametroPistones(TablaAuxiliarDetalle medidaDiametroPistones) {
		this.medidaDiametroPistones = medidaDiametroPistones;
	}

	public BigDecimal getLongitudPistones() {
		return longitudPistones;
	}

	public void setLongitudPistones(BigDecimal longitudPistones) {
		this.longitudPistones = longitudPistones;
	}

	public Integer getCantidadPistones() {
		return cantidadPistones;
	}

	public void setCantidadPistones(Integer cantidadPistones) {
		this.cantidadPistones = cantidadPistones;
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