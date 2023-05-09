package com.example.magra.erp.models.entity.produccion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prod_produccion_registro_estructura")
public class ProduccionRegistroEstructura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajo ordenTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private OrdenTrabajoDetalle ordenTrabajoDetalle;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaIngreso;

	@Column(length=200)
	private String responsable;
	
	private Integer cantVarillas;
	
	private String diametroFierroVarillas;
	
	private Integer cantAnillos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle  diametroFierroAnillos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle nroAlambreEspiral;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle nroAlambreAmarre;
	
	private String cantRoldana;
	
	private Integer cantElectrodos;
	
	private Boolean indConforme;
	
	@Column(length=400)
	private String observacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public OrdenTrabajoDetalle getOrdenTrabajoDetalle() {
		return ordenTrabajoDetalle;
	}

	public void setOrdenTrabajoDetalle(OrdenTrabajoDetalle ordenTrabajoDetalle) {
		this.ordenTrabajoDetalle = ordenTrabajoDetalle;
	}

	public Date getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Integer getCantVarillas() {
		return cantVarillas;
	}

	public void setCantVarillas(Integer cantVarillas) {
		this.cantVarillas = cantVarillas;
	}

	public String getDiametroFierroVarillas() {
		return diametroFierroVarillas;
	}

	public void setDiametroFierroVarillas(String diametroFierroVarillas) {
		this.diametroFierroVarillas = diametroFierroVarillas;
	}

	public Integer getCantAnillos() {
		return cantAnillos;
	}

	public void setCantAnillos(Integer cantAnillos) {
		this.cantAnillos = cantAnillos;
	}

	public TablaAuxiliarDetalle getDiametroFierroAnillos() {
		return diametroFierroAnillos;
	}

	public void setDiametroFierroAnillos(TablaAuxiliarDetalle diametroFierroAnillos) {
		this.diametroFierroAnillos = diametroFierroAnillos;
	}

	public TablaAuxiliarDetalle getNroAlambreEspiral() {
		return nroAlambreEspiral;
	}

	public void setNroAlambreEspiral(TablaAuxiliarDetalle nroAlambreEspiral) {
		this.nroAlambreEspiral = nroAlambreEspiral;
	}

	public TablaAuxiliarDetalle getNroAlambreAmarre() {
		return nroAlambreAmarre;
	}

	public void setNroAlambreAmarre(TablaAuxiliarDetalle nroAlambreAmarre) {
		this.nroAlambreAmarre = nroAlambreAmarre;
	}

	public String getCantRoldana() {
		return cantRoldana;
	}

	public void setCantRoldana(String cantRoldana) {
		this.cantRoldana = cantRoldana;
	}

	public Integer getCantElectrodos() {
		return cantElectrodos;
	}

	public void setCantElectrodos(Integer cantElectrodos) {
		this.cantElectrodos = cantElectrodos;
	}

	public Boolean getIndConforme() {
		return indConforme;
	}

	public void setIndConforme(Boolean indConforme) {
		this.indConforme = indConforme;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	private static final long serialVersionUID = 1L;

}