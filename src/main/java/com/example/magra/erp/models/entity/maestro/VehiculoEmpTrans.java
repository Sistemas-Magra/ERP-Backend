package com.example.magra.erp.models.entity.maestro;

import java.io.Serializable;
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
@Table(name="mae_vehiculo_empresa_transporte")
public class VehiculoEmpTrans implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=10)
	private String placaDelantera;
	
	@Column(length=10)
	private String placaTrasera;
	
	@Column(length=50)
	private String marca;
	
	@Column(length=25)
	private String stringBusqueda;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoVehiculo;

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlacaDelantera() {
		return placaDelantera;
	}

	public void setPlacaDelantera(String placaDelantera) {
		this.placaDelantera = placaDelantera;
	}

	public String getPlacaTrasera() {
		return placaTrasera;
	}

	public void setPlacaTrasera(String placaTrasera) {
		this.placaTrasera = placaTrasera;
	}

	public String getStringBusqueda() {
		return stringBusqueda;
	}

	public void setStringBusqueda(String stringBusqueda) {
		this.stringBusqueda = stringBusqueda;
	}

	public TablaAuxiliarDetalle getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TablaAuxiliarDetalle tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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
