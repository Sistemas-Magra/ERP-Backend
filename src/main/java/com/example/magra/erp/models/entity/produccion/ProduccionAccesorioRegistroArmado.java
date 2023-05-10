package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_accesorio_registro_armado")
public class ProduccionAccesorioRegistroArmado implements Serializable {

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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora;
	
	private Integer cantVarillas;
	
	private String longitudesVarillasDiametro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle nroAlambreAmarre;
	
	private String cantRoldanas;
	
	private Integer cantElectrodos;
	
	private Integer cantidad;
	
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

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Integer getCantVarillas() {
		return cantVarillas;
	}

	public void setCantVarillas(Integer cantVarillas) {
		this.cantVarillas = cantVarillas;
	}

	public String getLongitudesVarillasDiametro() {
		return longitudesVarillasDiametro;
	}

	public void setLongitudesVarillasDiametro(String longitudesVarillasDiametro) {
		this.longitudesVarillasDiametro = longitudesVarillasDiametro;
	}

	public TablaAuxiliarDetalle getNroAlambreAmarre() {
		return nroAlambreAmarre;
	}

	public void setNroAlambreAmarre(TablaAuxiliarDetalle nroAlambreAmarre) {
		this.nroAlambreAmarre = nroAlambreAmarre;
	}

	public String getCantRoldanas() {
		return cantRoldanas;
	}

	public void setCantRoldanas(String cantRoldanas) {
		this.cantRoldanas = cantRoldanas;
	}

	public Integer getCantElectrodos() {
		return cantElectrodos;
	}

	public void setCantElectrodos(Integer cantElectrodos) {
		this.cantElectrodos = cantElectrodos;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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