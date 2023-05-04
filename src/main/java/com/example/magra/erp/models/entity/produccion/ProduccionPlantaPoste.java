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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_planta_poste")
public class ProduccionPlantaPoste implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer nroProduccion;
	
	private Integer cantidad;
	
	@Column(length=20)
	private String stickerProduccion;
	
	@Column(length=20)
	private String stickerCalidad;
	
	private Integer idUsuarioCalidad;
	
	private Boolean indConformidad;
	
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajo ordenTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;
	
	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNroProduccion() {
		return nroProduccion;
	}

	public void setNroProduccion(Integer nroProduccion) {
		this.nroProduccion = nroProduccion;
	}

	public String getStickerProduccion() {
		return stickerProduccion;
	}

	public void setStickerProduccion(String stickerProduccion) {
		this.stickerProduccion = stickerProduccion;
	}

	public String getStickerCalidad() {
		return stickerCalidad;
	}

	public Integer getIdUsuarioCalidad() {
		return idUsuarioCalidad;
	}

	public void setIdUsuarioCalidad(Integer idUsuarioCalidad) {
		this.idUsuarioCalidad = idUsuarioCalidad;
	}

	public void setStickerCalidad(String stickerCalidad) {
		this.stickerCalidad = stickerCalidad;
	}

	public Boolean getIndConformidad() {
		return indConformidad;
	}

	public void setIndConformidad(Boolean indConformidad) {
		this.indConformidad = indConformidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public Integer getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	private static final long serialVersionUID = 1L;

}