package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.Planta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_stock_mensual")
public class StockMensualOrdenTrabajoDetalle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idMes;
	
	private Integer stock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Planta planta;
	
	public Integer getIdMes() {
		return idMes;
	}
	
	public void setIdMes(Integer idMes) {
		this.idMes = idMes;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public OrdenTrabajoDetalle getOrdenTrabajoDetalle() {
		return ordenTrabajoDetalle;
	}
	
	public void setOrdenTrabajoDetalle(OrdenTrabajoDetalle ordenTrabajoDetalle) {
		this.ordenTrabajoDetalle = ordenTrabajoDetalle;
	}
	
	private static final long serialVersionUID = 1L;

}
