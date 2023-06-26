package com.example.magra.erp.models.entity.maestro;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mae_producto_venta")
public class ProductoVenta  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String codigo;
	
	@Column(length=100)
	private String nombre;

	@Column(precision =5, scale=2)
	private BigDecimal longitud;

	@Column(precision =10, scale=2)
	private BigDecimal cargaTrabajo;

	@Column(precision =10, scale=2)
	private BigDecimal cima;

	@Column(precision =10, scale=2)
	private BigDecimal base;

	@Column(precision =10, scale=2)
	private BigDecimal peso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoProducto;

	@Column(precision =10, scale=2)
	private Double precioVentaBase;
	
	@Column(length=50)
	private String resumen;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_venta_id")
	private List<MateriaPrimaProducto> insumos;

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

	public TablaAuxiliarDetalle getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TablaAuxiliarDetalle tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Double getPrecioVentaBase() {
		return precioVentaBase;
	}

	public void setPrecioVentaBase(Double precioVentaBase) {
		this.precioVentaBase = precioVentaBase;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MateriaPrimaProducto> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<MateriaPrimaProducto> insumos) {
		this.insumos = insumos;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public BigDecimal getCargaTrabajo() {
		return cargaTrabajo;
	}

	public void setCargaTrabajo(BigDecimal cargaTrabajo) {
		this.cargaTrabajo = cargaTrabajo;
	}

	public BigDecimal getCima() {
		return cima;
	}

	public void setCima(BigDecimal cima) {
		this.cima = cima;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;
}