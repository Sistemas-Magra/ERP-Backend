package com.example.magra.erp.models.entity.talento_humano;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.Empleado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="emp_asistencia")
public class AsistenciaPersonal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaIngreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSalida;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Empleado empleado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoMarcacion;
	
	@Column(precision =10, scale=2)
	private BigDecimal horasExtras;
	
	@Column(precision =10, scale=2)
	private BigDecimal horasTardanza;
	
	@Column(precision =10, scale=2)
	private BigDecimal horasTrabajadas;
	
	@Column(precision =10, scale=2)
	private BigDecimal horasNoTrabajadas;
	
	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public TablaAuxiliarDetalle getTipoMarcacion() {
		return tipoMarcacion;
	}

	public void setTipoMarcacion(TablaAuxiliarDetalle tipoMarcacion) {
		this.tipoMarcacion = tipoMarcacion;
	}

	public BigDecimal getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(BigDecimal horasExtras) {
		this.horasExtras = horasExtras;
	}

	public BigDecimal getHorasTardanza() {
		return horasTardanza;
	}

	public void setHorasTardanza(BigDecimal horasTardanza) {
		this.horasTardanza = horasTardanza;
	}

	public BigDecimal getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(BigDecimal horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;

}
