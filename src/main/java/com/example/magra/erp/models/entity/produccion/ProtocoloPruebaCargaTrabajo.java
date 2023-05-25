package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_protocolo_prueba_carga_trabajo")
public class ProtocoloPruebaCargaTrabajo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "protocolo_prueba_carga_trabajo_id")
	private List<ProtocoloPruebaCargaTrabajoMuestra> muestras;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProtocoloPruebaCargaTrabajoMuestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<ProtocoloPruebaCargaTrabajoMuestra> muestras) {
		this.muestras = muestras;
	}

	private static final long serialVersionUID = 1L;

}