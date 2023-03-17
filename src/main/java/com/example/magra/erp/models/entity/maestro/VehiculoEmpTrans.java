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
@Table(name="mae_vehiculo_empresa_transporte")
public class VehiculoEmpTrans implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=10)
	private String placaDelantera;
	
	@Column(length=10)
	private String placaTrasera;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoVehiculo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlacaDelantera() {
		return placaDelantera;
	}

	public void setPlacaDelantera(String placaDelantera) {
		this.placaDelantera = placaDelantera;
	}

	public String getPlacaTrasera() {
		return placaTrasera;
	}

	public void setPlacaTrasera(String placaTrasera) {
		this.placaTrasera = placaTrasera;
	}

	public TablaAuxiliarDetalle getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TablaAuxiliarDetalle tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
