package com.example.magra.erp.models.entity.ventas;

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

import com.example.magra.erp.models.entity.maestro.ProductoVenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ven_orden_venta_detalle")
public class OrdenVentaDetalle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;

	@Column(precision =10, scale=2)	
	private Double precioVentaUnitario;

	@Column(precision =10, scale=2)	
	private Double descuentoPorcentaje;

	@Column(precision =10, scale=2)	
	private Double descuentoMonto;

	@Column(precision =10, scale=2)	
	private Double total;
	
	@Column(length=255)
	private String plano;
	
	@Column(length=255)
	private String especificacionesTecnicas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ProductoVenta producto;

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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioVentaUnitario() {
		return precioVentaUnitario;
	}

	public void setPrecioVentaUnitario(Double precioVentaUnitario) {
		this.precioVentaUnitario = precioVentaUnitario;
	}

	public Double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(Double descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	public Double getDescuentoMonto() {
		return descuentoMonto;
	}

	public void setDescuentoMonto(Double descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ProductoVenta getProducto() {
		return producto;
	}

	public void setProducto(ProductoVenta producto) {
		this.producto = producto;
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

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getEspecificacionesTecnicas() {
		return especificacionesTecnicas;
	}

	public void setEspecificacionesTecnicas(String especificacionesTecnicas) {
		this.especificacionesTecnicas = especificacionesTecnicas;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;
}