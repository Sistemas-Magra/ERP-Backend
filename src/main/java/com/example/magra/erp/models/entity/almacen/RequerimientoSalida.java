package com.example.magra.erp.models.entity.almacen;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.maestro.Area;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="alm_requerimiento_salida")
public class RequerimientoSalida implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idUsuarioSolicita;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Area areaSolicitante;
	
	private Integer idUsuarioSolicitante;
	
	
	
	private static final long serialVersionUID = 1L;

}
