package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="prod_produccion_registro_mezcla")
public class ProduccionRegistroMezcla implements Serializable {

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
	
	@Column(precision=10, scale=2)
	private BigDecimal bolsasCemento;

	@Column(precision=10, scale=2)
	private BigDecimal carretillaPiedra;

	@Column(precision=10, scale=2)
	private BigDecimal carretillaArena;
	
	private Integer tipoCemento;

	@Column(length=200)
	private String marcaCemento;

	@Column(precision=10, scale=2)
	private BigDecimal litrosAgua;

	@Column(precision=10, scale=2)
	private BigDecimal relacionAguaCemento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaIngreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSalida;
	
	private Integer minutosTotal;
	
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

	public BigDecimal getBolsasCemento() {
		return bolsasCemento;
	}

	public void setBolsasCemento(BigDecimal bolsasCemento) {
		this.bolsasCemento = bolsasCemento;
	}

	public BigDecimal getCarretillaPiedra() {
		return carretillaPiedra;
	}

	public void setCarretillaPiedra(BigDecimal carretillaPiedra) {
		this.carretillaPiedra = carretillaPiedra;
	}

	public BigDecimal getCarretillaArena() {
		return carretillaArena;
	}

	public void setCarretillaArena(BigDecimal carretillaArena) {
		this.carretillaArena = carretillaArena;
	}

	public Integer getTipoCemento() {
		return tipoCemento;
	}

	public void setTipoCemento(Integer tipoCemento) {
		this.tipoCemento = tipoCemento;
	}

	public String getMarcaCemento() {
		return marcaCemento;
	}

	public void setMarcaCemento(String marcaCemento) {
		this.marcaCemento = marcaCemento;
	}

	public BigDecimal getLitrosAgua() {
		return litrosAgua;
	}

	public void setLitrosAgua(BigDecimal litrosAgua) {
		this.litrosAgua = litrosAgua;
	}

	public BigDecimal getRelacionAguaCemento() {
		return relacionAguaCemento;
	}

	public void setRelacionAguaCemento(BigDecimal relacionAguaCemento) {
		this.relacionAguaCemento = relacionAguaCemento;
	}

	public Date getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Integer getMinutosTotal() {
		return minutosTotal;
	}

	public void setMinutosTotal(Integer minutosTotal) {
		this.minutosTotal = minutosTotal;
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