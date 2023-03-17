package com.example.magra.erp.models.entity.sig;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.CaracteristicaReporte;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="seg_reporte_caracteristica_encontrada")
public class ReporteCaracteristicaEncontrada implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private CaracteristicaReporte caracteristica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CaracteristicaReporte getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(CaracteristicaReporte caracteristica) {
		this.caracteristica = caracteristica;
	}

	private static final long serialVersionUID = 1L;

}