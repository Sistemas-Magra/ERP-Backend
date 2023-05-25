package com.example.magra.erp.models.entity.produccion;

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
@Table(name="prod_protocolo_prueba_rotura_muestra")
public class ProtocoloPruebaRoturaMuestra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(precision=10, scale=2)
	private BigDecimal valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle porcentaje;
	
	@Column(precision=10, scale=2)
	private BigDecimal kilogramosPrueba;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TablaAuxiliarDetalle getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(TablaAuxiliarDetalle porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getKilogramosPrueba() {
		return kilogramosPrueba;
	}

	public void setKilogramosPrueba(BigDecimal kilogramosPrueba) {
		this.kilogramosPrueba = kilogramosPrueba;
	}

	private static final long serialVersionUID = 1L;

}
