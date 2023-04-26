package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.ventas.OrdenVentaDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_orden_trabajo_detalle")
public class OrdenTrabajoDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenVentaDetalle ordenVentaDetalle;
	
	private Integer cantidadProducida;
	
	private Integer cantidadPendiente;
	
	private Integer cantidadProgramadaSemanal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdenVentaDetalle getOrdenVentaDetalle() {
		return ordenVentaDetalle;
	}

	public void setOrdenVentaDetalle(OrdenVentaDetalle ordenVentaDetalle) {
		this.ordenVentaDetalle = ordenVentaDetalle;
	}

	public Integer getCantidadProducida() {
		return cantidadProducida;
	}

	public void setCantidadProducida(Integer cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}

	public Integer getCantidadPendiente() {
		return cantidadPendiente;
	}

	public void setCantidadPendiente(Integer cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}

	public Integer getCantidadProgramadaSemanal() {
		return cantidadProgramadaSemanal;
	}

	public void setCantidadProgramadaSemanal(Integer cantidadProgramadaSemanal) {
		this.cantidadProgramadaSemanal = cantidadProgramadaSemanal;
	}

	private static final long serialVersionUID = 1L;
	
}
