package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.entity.maestro.ProductoVenta;
import com.example.magra.erp.models.entity.ventas.OrdenVenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "prod_protocolo_prueba")
public class ProtocoloPruebaCalidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 16)
	private String codigo;

	@Column(length = 200)
	private String entidadLicitante;

	private Integer lote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private OrdenVenta ordenVenta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private ProductoVenta producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private TablaAuxiliarDetalle tipoPrueba;

	@Column(precision = 10, scale = 2)
	private BigDecimal empotramiento;

	@Column(precision = 10, scale = 2)
	private BigDecimal coeficienteSeguridad;

	@Column(precision = 10, scale = 2)
	private BigDecimal porcentajeDeflexMax;

	@Column(precision = 10, scale = 2)
	private BigDecimal porcentajeDeformxMax;

	@Column(precision = 10, scale = 2)
	private BigDecimal deflexMax;

	@Column(precision = 10, scale = 2)
	private BigDecimal deformMax;

	@Column(length = 2000)
	private String conclusion;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "protocolo_prueba_id")
	private List<ProtocoloPruebaCargaTrabajo> pruebasCargaTrabajo;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "protocolo_prueba_id")
	private List<ProtocoloPruebaRotura> pruebasRotura;

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

	public String getEntidadLicitante() {
		return entidadLicitante;
	}

	public void setEntidadLicitante(String entidadLicitante) {
		this.entidadLicitante = entidadLicitante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OrdenVenta getOrdenVenta() {
		return ordenVenta;
	}

	public void setOrdenVenta(OrdenVenta ordenVenta) {
		this.ordenVenta = ordenVenta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public ProductoVenta getProducto() {
		return producto;
	}

	public void setProducto(ProductoVenta producto) {
		this.producto = producto;
	}

	public BigDecimal getCoeficienteSeguridad() {
		return coeficienteSeguridad;
	}

	public void setCoeficienteSeguridad(BigDecimal coeficienteSeguridad) {
		this.coeficienteSeguridad = coeficienteSeguridad;
	}

	public BigDecimal getPorcentajeDeformxMax() {
		return porcentajeDeformxMax;
	}

	public void setPorcentajeDeformxMax(BigDecimal porcentajeDeformxMax) {
		this.porcentajeDeformxMax = porcentajeDeformxMax;
	}

	public BigDecimal getPorcentajeDeflexMax() {
		return porcentajeDeflexMax;
	}

	public void setPorcentajeDeflexMax(BigDecimal porcentajeDeflexMax) {
		this.porcentajeDeflexMax = porcentajeDeflexMax;
	}

	public BigDecimal getDeflexMax() {
		return deflexMax;
	}

	public void setDeflexMax(BigDecimal deflexMax) {
		this.deflexMax = deflexMax;
	}

	public BigDecimal getDeformMax() {
		return deformMax;
	}

	public void setDeformMax(BigDecimal deformMax) {
		this.deformMax = deformMax;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public TablaAuxiliarDetalle getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(TablaAuxiliarDetalle tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

	public List<ProtocoloPruebaCargaTrabajo> getPruebasCargaTrabajo() {
		return pruebasCargaTrabajo;
	}

	public void setPruebasCargaTrabajo(List<ProtocoloPruebaCargaTrabajo> pruebasCargaTrabajo) {
		this.pruebasCargaTrabajo = pruebasCargaTrabajo;
	}

	public List<ProtocoloPruebaRotura> getPruebasRotura() {
		return pruebasRotura;
	}

	public void setPruebasRotura(List<ProtocoloPruebaRotura> pruebasRotura) {
		this.pruebasRotura = pruebasRotura;
	}

	public BigDecimal getEmpotramiento() {
		return empotramiento;
	}

	public void setEmpotramiento(BigDecimal empotramiento) {
		this.empotramiento = empotramiento;
	}

	private static final long serialVersionUID = 1L;

}