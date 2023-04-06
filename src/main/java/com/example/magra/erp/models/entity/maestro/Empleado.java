package com.example.magra.erp.models.entity.maestro;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.ubicacion.Distrito;
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

	@Column(length=100)
	private String nombreCompleto;
	
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCese;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estadoCivil;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle gradoInstruccion;

	@Column(length=300)
	private String ultimoLugarEstudio;	

	@Column(length=300)
	private String lugarNacimiento;	
	
	private Boolean indCasaPropia;
	
	private Boolean indEstaEnPlanilla;

	@Column(length=3)
	private String nroCalzado;	

	@Column(length=3)
	private String tallaPolo;	

	@Column(length=3)
	private String tallaPantalon;

	@Column(length=80)
	private String alergia;

	@Column(length=80)
	private String alergiaMedicamento;	

	@Column(length=20)
	private String telefonoEmergencia;	

	@Column(length=150)
	private String contactoEmergencia;	

	@Column(length=50)
	private String parentescoEmergencia;		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle ladoDominante;

	@Column(length=30)
	private String nacionalidad;	

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
	
	@Temporal(TemporalType.DATE)
	private Date fechaFinPrueba;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicioPrueba;
	
	private Integer puntuacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Banco bancoCts;
	
	@Column(length=30)
	private String cuentaCts;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Banco bancoSueldo;
	
	@Column(length=30)
	private String cuentaSueldo;

	private Integer idUsuarioCrea;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCrea;

	private Integer idUsuarioModifica;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModifica;

	@Column(length=200)
	private String foto;

	@Column(precision =10, scale=2)	
	private BigDecimal sueldoPrueba;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle periocidadPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Distrito distrito;
	
	private Boolean cobrarComisionAfp;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empleado_id")
	private List<HijoEmpleado> hijos;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empleado_id")
	private List<EmpleadoHorarios> horarios;

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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public TablaAuxiliarDetalle getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(TablaAuxiliarDetalle estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	public TablaAuxiliarDetalle getGradoInstruccion() {
		return gradoInstruccion;
	}

	public void setGradoInstruccion(TablaAuxiliarDetalle gradoInstruccion) {
		this.gradoInstruccion = gradoInstruccion;
	}

	public String getUltimoLugarEstudio() {
		return ultimoLugarEstudio;
	}

	public void setUltimoLugarEstudio(String ultimoLugarEstudio) {
		this.ultimoLugarEstudio = ultimoLugarEstudio;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Boolean getIndCasaPropia() {
		return indCasaPropia;
	}

	public void setIndCasaPropia(Boolean indCasaPropia) {
		this.indCasaPropia = indCasaPropia;
	}

	public String getNroCalzado() {
		return nroCalzado;
	}

	public void setNroCalzado(String nroCalzado) {
		this.nroCalzado = nroCalzado;
	}

	public String getTallaPolo() {
		return tallaPolo;
	}

	public void setTallaPolo(String tallaPolo) {
		this.tallaPolo = tallaPolo;
	}

	public String getTallaPantalon() {
		return tallaPantalon;
	}

	public void setTallaPantalon(String tallaPantalon) {
		this.tallaPantalon = tallaPantalon;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getAlergiaMedicamento() {
		return alergiaMedicamento;
	}

	public void setAlergiaMedicamento(String alergiaMedicamento) {
		this.alergiaMedicamento = alergiaMedicamento;
	}

	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	public String getParentescoEmergencia() {
		return parentescoEmergencia;
	}

	public void setParentescoEmergencia(String parentescoEmergencia) {
		this.parentescoEmergencia = parentescoEmergencia;
	}

	public TablaAuxiliarDetalle getLadoDominante() {
		return ladoDominante;
	}

	public void setLadoDominante(TablaAuxiliarDetalle ladoDominante) {
		this.ladoDominante = ladoDominante;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<HijoEmpleado> getHijos() {
		return hijos;
	}

	public void setHijos(List<HijoEmpleado> hijos) {
		this.hijos = hijos;
	}

	public Banco getBancoCts() {
		return bancoCts;
	}

	public void setBancoCts(Banco bancoCts) {
		this.bancoCts = bancoCts;
	}

	public String getCuentaCts() {
		return cuentaCts;
	}

	public void setCuentaCts(String cuentaCts) {
		this.cuentaCts = cuentaCts;
	}

	public Banco getBancoSueldo() {
		return bancoSueldo;
	}

	public void setBancoSueldo(Banco bancoSueldo) {
		this.bancoSueldo = bancoSueldo;
	}

	public String getCuentaSueldo() {
		return cuentaSueldo;
	}

	public void setCuentaSueldo(String cuentaSueldo) {
		this.cuentaSueldo = cuentaSueldo;
	}

	public Date getFechaInicioPrueba() {
		return fechaInicioPrueba;
	}

	public void setFechaInicioPrueba(Date fechaInicioPrueba) {
		this.fechaInicioPrueba = fechaInicioPrueba;
	}

	public BigDecimal getSueldoPrueba() {
		return sueldoPrueba;
	}

	public void setSueldoPrueba(BigDecimal sueldoPrueba) {
		this.sueldoPrueba = sueldoPrueba;
	}

	public TablaAuxiliarDetalle getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TablaAuxiliarDetalle tipoPago) {
		this.tipoPago = tipoPago;
	}

	public TablaAuxiliarDetalle getPeriocidadPago() {
		return periocidadPago;
	}

	public void setPeriocidadPago(TablaAuxiliarDetalle periocidadPago) {
		this.periocidadPago = periocidadPago;
	}

	public Boolean getCobrarComisionAfp() {
		return cobrarComisionAfp;
	}

	public void setCobrarComisionAfp(Boolean cobrarComisionAfp) {
		this.cobrarComisionAfp = cobrarComisionAfp;
	}

	public Boolean getIndEstaEnPlanilla() {
		return indEstaEnPlanilla;
	}

	public void setIndEstaEnPlanilla(Boolean indEstaEnPlanilla) {
		this.indEstaEnPlanilla = indEstaEnPlanilla;
	}

	public List<EmpleadoHorarios> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<EmpleadoHorarios> horarios) {
		this.horarios = horarios;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;

}
