package com.example.magra.erp.models.entity.transporte;

import java.io.Serializable;
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

import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;
import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;
import com.example.magra.erp.models.entity.maestro.VehiculoEmpTrans;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="trans_formulario")
public class Formulario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=15)
	private String codigo;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDespacho;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmpresaTransporte empresaTransporte;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ConductorEmpTrans conductor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private VehiculoEmpTrans vehiculo;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDespachoCronograma;
	
	private Boolean indAtendido;
	
	private Integer idUsuarioSeguridadVerifica;
	
	private Boolean indVerificacionSuperficie;
	
	private Boolean indSctr;
	
	private Boolean indCasco;
	
	private Boolean indZapato;
	
	private Boolean indChaleco;
	
	private Boolean indDni;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

	public EmpresaTransporte getEmpresaTransporte() {
		return empresaTransporte;
	}

	public void setEmpresaTransporte(EmpresaTransporte empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}

	public ConductorEmpTrans getConductor() {
		return conductor;
	}

	public void setConductor(ConductorEmpTrans conductor) {
		this.conductor = conductor;
	}

	public VehiculoEmpTrans getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEmpTrans vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaDespachoCronograma() {
		return fechaDespachoCronograma;
	}

	public void setFechaDespachoCronograma(Date fechaDespachoCronograma) {
		this.fechaDespachoCronograma = fechaDespachoCronograma;
	}

	public Boolean getIndAtendido() {
		return indAtendido;
	}

	public void setIndAtendido(Boolean indAtendido) {
		this.indAtendido = indAtendido;
	}

	public Integer getIdUsuarioSeguridadVerifica() {
		return idUsuarioSeguridadVerifica;
	}

	public void setIdUsuarioSeguridadVerifica(Integer idUsuarioSeguridadVerifica) {
		this.idUsuarioSeguridadVerifica = idUsuarioSeguridadVerifica;
	}

	public Boolean getIndVerificacionSuperficie() {
		return indVerificacionSuperficie;
	}

	public void setIndVerificacionSuperficie(Boolean indVerificacionSuperficie) {
		this.indVerificacionSuperficie = indVerificacionSuperficie;
	}

	public Boolean getIndSctr() {
		return indSctr;
	}

	public void setIndSctr(Boolean indSctr) {
		this.indSctr = indSctr;
	}

	public Boolean getIndCasco() {
		return indCasco;
	}

	public void setIndCasco(Boolean indCasco) {
		this.indCasco = indCasco;
	}

	public Boolean getIndZapato() {
		return indZapato;
	}

	public void setIndZapato(Boolean indZapato) {
		this.indZapato = indZapato;
	}

	public Boolean getIndChaleco() {
		return indChaleco;
	}

	public void setIndChaleco(Boolean indChaleco) {
		this.indChaleco = indChaleco;
	}

	public Boolean getIndDni() {
		return indDni;
	}

	public void setIndDni(Boolean indDni) {
		this.indDni = indDni;
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
