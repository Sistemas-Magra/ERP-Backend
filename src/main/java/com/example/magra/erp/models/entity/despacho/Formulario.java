package com.example.magra.erp.models.entity.despacho;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;
import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;
import com.example.magra.erp.models.entity.maestro.VehiculoEmpTrans;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="desp_formulario")
public class Formulario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String nroRemision;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(length=400)
	private String destino;
	
	private Integer cantReglas;
	
	private Integer cantTacos;
	
	private Integer cantClavos;
	
	private Boolean indVerificacionSuperficie;
	
	private Boolean indConfListones;
	
	private Boolean indConfReglas;
	
	private Boolean indConfClavos;
	
	private Integer cantRealListones;
	
	private Integer cantRealReglas;
	
	private Integer cantRealClavos;
	
	private Boolean indSctr;
	
	private Boolean indCasco;
	
	private Boolean indZapato;
	
	private Boolean indChaleco;
	
	private Boolean indDni;
	
	@Column(length=400)
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajo ordenTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmpresaTransporte empresaTransporte;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private VehiculoEmpTrans vehiculo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ConductorEmpTrans conductor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle motivoTraslado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "formulario_id")
	private List<FormularioDetalle> detalle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNroRemision() {
		return nroRemision;
	}

	public void setNroRemision(String nroRemision) {
		this.nroRemision = nroRemision;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	public Integer getCantReglas() {
		return cantReglas;
	}

	public void setCantReglas(Integer cantReglas) {
		this.cantReglas = cantReglas;
	}

	public Integer getCantTacos() {
		return cantTacos;
	}

	public void setCantTacos(Integer cantTacos) {
		this.cantTacos = cantTacos;
	}

	public Integer getCantClavos() {
		return cantClavos;
	}

	public void setCantClavos(Integer cantClavos) {
		this.cantClavos = cantClavos;
	}

	public Boolean getIndVerificacionSuperficie() {
		return indVerificacionSuperficie;
	}

	public void setIndVerificacionSuperficie(Boolean indVerificacionSuperficie) {
		this.indVerificacionSuperficie = indVerificacionSuperficie;
	}

	public Boolean getIndConfListones() {
		return indConfListones;
	}

	public void setIndConfListones(Boolean indConfListones) {
		this.indConfListones = indConfListones;
	}

	public Boolean getIndConfReglas() {
		return indConfReglas;
	}

	public void setIndConfReglas(Boolean indConfReglas) {
		this.indConfReglas = indConfReglas;
	}

	public Boolean getIndConfClavos() {
		return indConfClavos;
	}

	public void setIndConfClavos(Boolean indConfClavos) {
		this.indConfClavos = indConfClavos;
	}

	public Integer getCantRealListones() {
		return cantRealListones;
	}

	public void setCantRealListones(Integer cantRealListones) {
		this.cantRealListones = cantRealListones;
	}

	public Integer getCantRealReglas() {
		return cantRealReglas;
	}

	public void setCantRealReglas(Integer cantRealReglas) {
		this.cantRealReglas = cantRealReglas;
	}

	public Integer getCantRealClavos() {
		return cantRealClavos;
	}

	public void setCantRealClavos(Integer cantRealClavos) {
		this.cantRealClavos = cantRealClavos;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EmpresaTransporte getEmpresaTransporte() {
		return empresaTransporte;
	}

	public void setEmpresaTransporte(EmpresaTransporte empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}

	public VehiculoEmpTrans getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEmpTrans vehiculo) {
		this.vehiculo = vehiculo;
	}

	public ConductorEmpTrans getConductor() {
		return conductor;
	}

	public void setConductor(ConductorEmpTrans conductor) {
		this.conductor = conductor;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public TablaAuxiliarDetalle getMotivoTraslado() {
		return motivoTraslado;
	}

	public void setMotivoTraslado(TablaAuxiliarDetalle motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
	}

	public List<FormularioDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<FormularioDetalle> detalle) {
		this.detalle = detalle;
	}

	private static final long serialVersionUID = 1L;
	
}