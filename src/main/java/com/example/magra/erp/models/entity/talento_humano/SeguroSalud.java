package com.example.magra.erp.models.entity.talento_humano;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name="emp_seguro_salud")
public class SeguroSalud implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String abreviatura;
	
	@Column(length=200)
	private String nombre;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal montoPorcentajePago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getMontoPorcentajePago() {
		return montoPorcentajePago;
	}

	public void setMontoPorcentajePago(BigDecimal montoPorcentajePago) {
		this.montoPorcentajePago = montoPorcentajePago;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	private static final long serialVersionUID = 1L;

}
