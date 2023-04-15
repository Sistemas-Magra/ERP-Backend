package com.example.magra.erp.models.entity.ventas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.ProductoVenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ven_orden_venta_despacho_detalle")
public class OrdenVentaDespachoDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ProductoVenta producto;

    private Integer precioTotal;
	
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

	public ProductoVenta getProducto() {
		return producto;
	}

	public void setProducto(ProductoVenta producto) {
		this.producto = producto;
	}

	public Integer getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}

	private static final long serialVersionUID = 8253808573718105007L;

}