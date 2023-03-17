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
@Table(name="mae_requisito_cargo")
public class RequisitoCargo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=250)
	private String descripcion;

	@Column(precision =10, scale=2)
	private Double cantidadExperiencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle unidadMedida;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidadExperiencia() {
		return cantidadExperiencia;
	}

	public void setCantidadExperiencia(Double cantidadExperiencia) {
		this.cantidadExperiencia = cantidadExperiencia;
	}

	public TablaAuxiliarDetalle getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(TablaAuxiliarDetalle unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	private static final long serialVersionUID = 1L;
	
}