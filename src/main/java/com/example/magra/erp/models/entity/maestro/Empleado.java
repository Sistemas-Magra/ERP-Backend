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
@Table(name="mae_empleado")
public class Empleado implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String codigo;
	
	@Column(length=200)
	private String nombres;
	
	@Column(length=100)
	private String apellidoPaterno;

	@Column(length=100)
	private String apellidoMaterno;
	
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCese;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle motivoCese;

	@Column(length=300)
	private String direccion;

	@Column(length=20)
	private String celular;

	@Column(length=20)
	private String telefono;

	@Column(length=200)
	private String correo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle categoria;
	
	private Integer vacacionesAcumuladas;
	
	private Integer vacacionesDisponibles;
	
	private Integer vacacionesOcupadas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoDocumentoIdentidad;

	@Column(length=20)
	private String nroDocumentoIdentidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private EntidadFondos entidadFondos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private SubArea subArea;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Cargo cargo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFinPrueba;
	
	private Integer puntuacion;

	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	private Integer idUsuarioModifica;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModifica;

	@Column(length=200)
	private String foto;

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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaCese() {
		return fechaCese;
	}

	public void setFechaCese(Date fechaCese) {
		this.fechaCese = fechaCese;
	}

	public TablaAuxiliarDetalle getMotivoCese() {
		return motivoCese;
	}

	public void setMotivoCese(TablaAuxiliarDetalle motivoCese) {
		this.motivoCese = motivoCese;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public TablaAuxiliarDetalle getCategoria() {
		return categoria;
	}

	public void setCategoria(TablaAuxiliarDetalle categoria) {
		this.categoria = categoria;
	}

	public Integer getVacacionesAcumuladas() {
		return vacacionesAcumuladas;
	}

	public void setVacacionesAcumuladas(Integer vacacionesAcumuladas) {
		this.vacacionesAcumuladas = vacacionesAcumuladas;
	}

	public Integer getVacacionesDisponibles() {
		return vacacionesDisponibles;
	}

	public void setVacacionesDisponibles(Integer vacacionesDisponibles) {
		this.vacacionesDisponibles = vacacionesDisponibles;
	}

	public Integer getVacacionesOcupadas() {
		return vacacionesOcupadas;
	}

	public void setVacacionesOcupadas(Integer vacacionesOcupadas) {
		this.vacacionesOcupadas = vacacionesOcupadas;
	}

	public TablaAuxiliarDetalle getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(TablaAuxiliarDetalle tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	public String getNroDocumentoIdentidad() {
		return nroDocumentoIdentidad;
	}

	public void setNroDocumentoIdentidad(String nroDocumentoIdentidad) {
		this.nroDocumentoIdentidad = nroDocumentoIdentidad;
	}

	public EntidadFondos getEntidadFondos() {
		return entidadFondos;
	}

	public void setEntidadFondos(EntidadFondos entidadFondos) {
		this.entidadFondos = entidadFondos;
	}

	public SubArea getSubArea() {
		return subArea;
	}

	public void setSubArea(SubArea subArea) {
		this.subArea = subArea;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getFechaFinPrueba() {
		return fechaFinPrueba;
	}

	public void setFechaFinPrueba(Date fechaFinPrueba) {
		this.fechaFinPrueba = fechaFinPrueba;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;

}
