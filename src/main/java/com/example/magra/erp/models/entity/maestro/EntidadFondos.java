package com.example.magra.erp.models.entity.maestro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mae_entidad_fondos")
public class EntidadFondos implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=200)
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoEntidadFondos;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal comisionFlujo;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal prima;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal aporte;	

	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	private Integer idUsuarioModifica;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModifica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TablaAuxiliarDetalle getTipoEntidadFondos() {
		return tipoEntidadFondos;
	}

	public void setTipoEntidadFondos(TablaAuxiliarDetalle tipoEntidadFondos) {
		this.tipoEntidadFondos = tipoEntidadFondos;
	}

	public BigDecimal getComisionFlujo() {
		return comisionFlujo;
	}

	public void setComisionFlujo(BigDecimal comisionFlujo) {
		this.comisionFlujo = comisionFlujo;
	}

	public BigDecimal getPrima() {
		return prima;
	}

	public void setPrima(BigDecimal prima) {
		this.prima = prima;
	}

	public BigDecimal getAporte() {
		return aporte;
	}

	public void setAporte(BigDecimal aporte) {
		this.aporte = aporte;
	}

	public Integer getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Integer getIdUsuarioModifica() {
		return idUsuarioModifica;
	}

	public void setIdUsuarioModifica(Integer idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;
}