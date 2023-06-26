package com.example.magra.erp.models.entity.despacho;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajoDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="desp_formulario_detalle")
public class FormularioDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;
	
	private Boolean indCarga;
	
	private Integer cantidadSolicitada;
	
	private Integer cantidadPendDespacho;
	
	private Integer cantidadDisponible;
	
	private Integer cantidad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdenTrabajoDetalle getOrdenTrabajoDetalle() {
		return ordenTrabajoDetalle;
	}

	public void setOrdenTrabajoDetalle(OrdenTrabajoDetalle ordenTrabajoDetalle) {
		this.ordenTrabajoDetalle = ordenTrabajoDetalle;
	}

	public Boolean getIndCarga() {
		return indCarga;
	}

	public void setIndCarga(Boolean indCarga) {
		this.indCarga = indCarga;
	}

	public Integer getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(Integer cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public Integer getCantidadPendDespacho() {
		return cantidadPendDespacho;
	}

	public void setCantidadPendDespacho(Integer cantidadPendDespacho) {
		this.cantidadPendDespacho = cantidadPendDespacho;
	}

	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	private static final long serialVersionUID = 1L;

}