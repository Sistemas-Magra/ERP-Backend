package com.example.magra.erp.models.entity.sig;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.Empleado;
import com.example.magra.erp.models.entity.maestro.Planta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="seg_reporte_actos_condiciones")
public class ReporteActosCondiciones implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idUsuarioReporta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Empleado empleado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoEvento;
	
	@Column(length=400)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle afectacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Planta planta;
	
	@Column(length=400)
	private String medidaMejora;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "reporte_id")
	private List<ReporteCondicionEncontrada> condiciones;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "reporte_id")
	private List<ReporteCaracteristicaEncontrada> caracteristicas;

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

	public Integer getIdUsuarioReporta() {
		return idUsuarioReporta;
	}

	public void setIdUsuarioReporta(Integer idUsuarioReporta) {
		this.idUsuarioReporta = idUsuarioReporta;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public TablaAuxiliarDetalle getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TablaAuxiliarDetalle tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TablaAuxiliarDetalle getAfectacion() {
		return afectacion;
	}

	public void setAfectacion(TablaAuxiliarDetalle afectacion) {
		this.afectacion = afectacion;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public String getMedidaMejora() {
		return medidaMejora;
	}

	public void setMedidaMejora(String medidaMejora) {
		this.medidaMejora = medidaMejora;
	}

	public List<ReporteCondicionEncontrada> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<ReporteCondicionEncontrada> condiciones) {
		this.condiciones = condiciones;
	}

	public List<ReporteCaracteristicaEncontrada> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<ReporteCaracteristicaEncontrada> caracteristicas) {
		this.caracteristicas = caracteristicas;
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

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;

}