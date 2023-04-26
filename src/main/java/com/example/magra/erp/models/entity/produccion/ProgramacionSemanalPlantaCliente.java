package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_programacion_semanal_planta_cliente")
public class ProgramacionSemanalPlantaCliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidadPendiente;
	
	private Integer cantidadProgramada;

	private Integer cantidadMoldes;

	private Integer vueltasMolde;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajo ordenTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "programacion_semanal_planta_cliente_id")
	private List<ProgramacionDiariaHistorico> detalleDiarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidadPendiente() {
		return cantidadPendiente;
	}

	public void setCantidadPendiente(Integer cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}

	public Integer getCantidadProgramada() {
		return cantidadProgramada;
	}

	public void setCantidadProgramada(Integer cantidadProgramada) {
		this.cantidadProgramada = cantidadProgramada;
	}

	public Integer getCantidadMoldes() {
		return cantidadMoldes;
	}

	public void setCantidadMoldes(Integer cantidadMoldes) {
		this.cantidadMoldes = cantidadMoldes;
	}

	public Integer getVueltasMolde() {
		return vueltasMolde;
	}

	public void setVueltasMolde(Integer vueltasMolde) {
		this.vueltasMolde = vueltasMolde;
	}

	public OrdenTrabajoDetalle getOrdenTrabajoDetalle() {
		return ordenTrabajoDetalle;
	}

	public void setOrdenTrabajoDetalle(OrdenTrabajoDetalle ordenTrabajoDetalle) {
		this.ordenTrabajoDetalle = ordenTrabajoDetalle;
	}

	public List<ProgramacionDiariaHistorico> getDetalleDiarios() {
		return detalleDiarios;
	}

	public void setDetalleDiarios(List<ProgramacionDiariaHistorico> detalleDiarios) {
		this.detalleDiarios = detalleDiarios;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	private static final long serialVersionUID = 1L;

}
