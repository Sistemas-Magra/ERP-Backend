package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.Planta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OrdenTrabajoDiaPoste implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String codigoProduccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaProduccion;
	
	private Integer idUsuarioCalidad;
	
	@Column(length = 50)
	private String codigoCalidad;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRevisionCalidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Planta planta;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "orden_trabajo_dia_id")
	private List<PosteEmpleado> empleados;
	
	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	private Integer idUsuarioModifica;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModifica;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoProduccion() {
		return codigoProduccion;
	}

	public void setCodigoProduccion(String codigoProduccion) {
		this.codigoProduccion = codigoProduccion;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	public Date getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(Date fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public Integer getIdUsuarioCalidad() {
		return idUsuarioCalidad;
	}

	public void setIdUsuarioCalidad(Integer idUsuarioCalidad) {
		this.idUsuarioCalidad = idUsuarioCalidad;
	}

	public String getCodigoCalidad() {
		return codigoCalidad;
	}

	public void setCodigoCalidad(String codigoCalidad) {
		this.codigoCalidad = codigoCalidad;
	}

	public Date getFechaRevisionCalidad() {
		return fechaRevisionCalidad;
	}

	public void setFechaRevisionCalidad(Date fechaRevisionCalidad) {
		this.fechaRevisionCalidad = fechaRevisionCalidad;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public List<PosteEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<PosteEmpleado> empleados) {
		this.empleados = empleados;
	}

	public Integer getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Integer getIdUsuarioModifica() {
		return idUsuarioModifica;
	}

	public void setIdUsuarioModifica(Integer idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

	private static final long serialVersionUID = 1L;

}
