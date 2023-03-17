package com.example.magra.erp.models.entity.almacen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.magra.erp.models.entity.maestro.Almacen;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="alm_nota_salida")
public class NotaSalida implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=10)
	private String codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Almacen almacenSalida;

	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	private Integer idUsuarioEntrega;

	private Integer idUsuarioRecibe;
	
	@Column(length=400)
	private String observacion;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "nota_entrada_id")
	private List<NotaSalidaDetalle> detalle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Almacen getAlmacenSalida() {
		return almacenSalida;
	}

	public void setAlmacenSalida(Almacen almacenSalida) {
		this.almacenSalida = almacenSalida;
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

	public Integer getIdUsuarioEntrega() {
		return idUsuarioEntrega;
	}

	public void setIdUsuarioEntrega(Integer idUsuarioEntrega) {
		this.idUsuarioEntrega = idUsuarioEntrega;
	}

	public Integer getIdUsuarioRecibe() {
		return idUsuarioRecibe;
	}

	public void setIdUsuarioRecibe(Integer idUsuarioRecibe) {
		this.idUsuarioRecibe = idUsuarioRecibe;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<NotaSalidaDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<NotaSalidaDetalle> detalle) {
		this.detalle = detalle;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;

}