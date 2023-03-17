package com.example.magra.erp.models.entity.sig;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.CondicionReporte;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="seg_reporte_condicion_encontrada")
public class ReporteCondicionEncontrada implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private CondicionReporte condicion;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CondicionReporte getCondicion() {
		return condicion;
	}

	public void setCondicion(CondicionReporte condicion) {
		this.condicion = condicion;
	}

	private static final long serialVersionUID = 1L;

}