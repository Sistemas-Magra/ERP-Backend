package com.example.magra.erp.models.entity.maestro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mae_materia_prima_producto")
public class MateriaPrimaProducto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(precision =10, scale=2)
	private Double cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle unidadMedida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private ProductoAlmacen insumo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public TablaAuxiliarDetalle getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(TablaAuxiliarDetalle unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public ProductoAlmacen getInsumo() {
		return insumo;
	}

	public void setInsumo(ProductoAlmacen insumo) {
		this.insumo = insumo;
	}

	private static final long serialVersionUID = 1L;
}
