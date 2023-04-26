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
@Table(name="prod_programacion_semanal_vigencia")
public class ProgramacionSemanalVigencia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer version;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "programacion_semanal_vigencia_id")
	private List<ProgramacionSemanal> versionesProgramacionSemanal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<ProgramacionSemanal> getVersionesProgramacionSemanal() {
		return versionesProgramacionSemanal;
	}

	public void setVersionesProgramacionSemanal(List<ProgramacionSemanal> versionesProgramacionSemanal) {
		this.versionesProgramacionSemanal = versionesProgramacionSemanal;
	}

	private static final long serialVersionUID = 1L;
	
}