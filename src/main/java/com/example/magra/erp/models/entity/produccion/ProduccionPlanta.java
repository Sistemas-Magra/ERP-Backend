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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.Planta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_planta")
public class ProduccionPlanta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Planta planta;
	
	private Boolean indProcesoCalidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionPlantaPoste> detallePostes;
	
	//Postes
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroMezcla> detalleMezcla;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroEstructura> detalleEstructura;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroTubosPines> detalleTubosPines;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroCentrifugado> detalleCentrifugado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroDesencrofado> detalleDesencrofado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionRegistroCurado> detalleCurado;
	
	//Accesorios
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionAccesorioRegistroMezcla> detalleMezclaAccesorios;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionAccesorioRegistroArmado> detalleArmadoAccesorios;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionAccesorioRegistroVibracion> detalleVibracionAccesorios;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produccion_planta_id")
	private List<ProduccionAccesorioRegistroAcabado> detalleAcabadoAccesorios;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Boolean getIndProcesoCalidad() {
		return indProcesoCalidad;
	}

	public void setIndProcesoCalidad(Boolean indProcesoCalidad) {
		this.indProcesoCalidad = indProcesoCalidad;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	public List<ProduccionPlantaPoste> getDetallePostes() {
		return detallePostes;
	}

	public void setDetallePostes(List<ProduccionPlantaPoste> detallePostes) {
		this.detallePostes = detallePostes;
	}

	public List<ProduccionRegistroMezcla> getDetalleMezcla() {
		return detalleMezcla;
	}

	public void setDetalleMezcla(List<ProduccionRegistroMezcla> detalleMezcla) {
		this.detalleMezcla = detalleMezcla;
	}

	public List<ProduccionRegistroEstructura> getDetalleEstructura() {
		return detalleEstructura;
	}

	public void setDetalleEstructura(List<ProduccionRegistroEstructura> detalleEstructura) {
		this.detalleEstructura = detalleEstructura;
	}

	public List<ProduccionRegistroTubosPines> getDetalleTubosPines() {
		return detalleTubosPines;
	}

	public void setDetalleTubosPines(List<ProduccionRegistroTubosPines> detalleTubosPines) {
		this.detalleTubosPines = detalleTubosPines;
	}

	public List<ProduccionRegistroCentrifugado> getDetalleCentrifugado() {
		return detalleCentrifugado;
	}

	public void setDetalleCentrifugado(List<ProduccionRegistroCentrifugado> detalleCentrifugado) {
		this.detalleCentrifugado = detalleCentrifugado;
	}

	public List<ProduccionRegistroDesencrofado> getDetalleDesencrofado() {
		return detalleDesencrofado;
	}

	public void setDetalleDesencrofado(List<ProduccionRegistroDesencrofado> detalleDesencrofado) {
		this.detalleDesencrofado = detalleDesencrofado;
	}

	public List<ProduccionRegistroCurado> getDetalleCurado() {
		return detalleCurado;
	}

	public void setDetalleCurado(List<ProduccionRegistroCurado> detalleCurado) {
		this.detalleCurado = detalleCurado;
	}

	public List<ProduccionAccesorioRegistroMezcla> getDetalleMezclaAccesorios() {
		return detalleMezclaAccesorios;
	}

	public void setDetalleMezclaAccesorios(List<ProduccionAccesorioRegistroMezcla> detalleMezclaAccesorios) {
		this.detalleMezclaAccesorios = detalleMezclaAccesorios;
	}

	public List<ProduccionAccesorioRegistroArmado> getDetalleArmadoAccesorios() {
		return detalleArmadoAccesorios;
	}

	public void setDetalleArmadoAccesorios(List<ProduccionAccesorioRegistroArmado> detalleArmadoAccesorios) {
		this.detalleArmadoAccesorios = detalleArmadoAccesorios;
	}

	public List<ProduccionAccesorioRegistroVibracion> getDetalleVibracionAccesorios() {
		return detalleVibracionAccesorios;
	}

	public void setDetalleVibracionAccesorios(List<ProduccionAccesorioRegistroVibracion> detalleVibracionAccesorios) {
		this.detalleVibracionAccesorios = detalleVibracionAccesorios;
	}

	public List<ProduccionAccesorioRegistroAcabado> getDetalleAcabadoAccesorios() {
		return detalleAcabadoAccesorios;
	}

	public void setDetalleAcabadoAccesorios(List<ProduccionAccesorioRegistroAcabado> detalleAcabadoAccesorios) {
		this.detalleAcabadoAccesorios = detalleAcabadoAccesorios;
	}

	private static final long serialVersionUID = 1L;
	
}