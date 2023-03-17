package com.example.magra.erp.models.entity.talento_humano;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.RequisitoCargo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="emp_requisito_campania")
public class RequisitoCampania implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private CampaniaBusquedaPersonal campania;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private RequisitoCargo requisito;

	private static final long serialVersionUID = 1L;
}
