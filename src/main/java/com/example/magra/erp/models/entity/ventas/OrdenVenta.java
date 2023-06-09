package com.example.magra.erp.models.entity.ventas;

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
import com.example.magra.erp.models.entity.gestion.Empresa;
import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.entity.maestro.ClienteContacto;
import com.example.magra.erp.models.entity.maestro.Moneda;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ven_orden_venta")
public class OrdenVenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=15)
	private String codigo;
	
	@Column(length = 1000)
	private String nombreTrabajo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Empresa empresaPartida;
	
	private Boolean indIncluyeIgv;
	
	private Integer diasValidez;

	@Column(precision =10, scale=2)	
	private BigDecimal aniosGarantia;

	@Column(precision =10, scale=2)	
	private BigDecimal plazoEntrega;

	@Column(precision =10, scale=2)	
	private BigDecimal subtotal;

	@Column(precision =10, scale=2)	
	private BigDecimal montoIgv;

	@Column(precision =10, scale=2)	
	private BigDecimal total;

	@Column(precision =10, scale=2)	
	private BigDecimal pagoPendiente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = false)
	private ClienteContacto contacto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle estado;
	
	@Temporal(TemporalType.DATE)
	private Date fechaEntregaBase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle metodoPago;

	@Column(precision =10, scale=2)	
	private BigDecimal adelanto;

	@Column(precision =10, scale=2)	
	private BigDecimal adelantoPorc;
	
	private Integer diasPagoCredito;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Moneda tipoMoneda;

	@Column(precision =10, scale=2)	
	private Double descuentoTotal;
	
	@Column(length=150)
	private String referencia;

	@Column(precision =10, scale=2)	
	private BigDecimal tipoCambio;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_venta_id")
	private List<Pago> pagos;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_venta_id")
	private List<OrdenVentaDetalle> detalle;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_venta_id")
	private List<OrdenVentaDespacho> despacho;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Empresa getEmpresaPartida() {
		return empresaPartida;
	}

	public void setEmpresaPartida(Empresa empresaPartida) {
		this.empresaPartida = empresaPartida;
	}

	public Boolean getIndIncluyeIgv() {
		return indIncluyeIgv;
	}

	public void setIndIncluyeIgv(Boolean indIncluyeIgv) {
		this.indIncluyeIgv = indIncluyeIgv;
	}

	public Integer getDiasValidez() {
		return diasValidez;
	}

	public void setDiasValidez(Integer diasValidez) {
		this.diasValidez = diasValidez;
	}

	public TablaAuxiliarDetalle getEstado() {
		return estado;
	}

	public void setEstado(TablaAuxiliarDetalle estado) {
		this.estado = estado;
	}

	public Date getFechaEntregaBase() {
		return fechaEntregaBase;
	}

	public void setFechaEntregaBase(Date fechaEntregaBase) {
		this.fechaEntregaBase = fechaEntregaBase;
	}

	public BigDecimal getAniosGarantia() {
		return aniosGarantia;
	}

	public void setAniosGarantia(BigDecimal aniosGarantia) {
		this.aniosGarantia = aniosGarantia;
	}

	public BigDecimal getPlazoEntrega() {
		return plazoEntrega;
	}

	public void setPlazoEntrega(BigDecimal plazoEntrega) {
		this.plazoEntrega = plazoEntrega;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getMontoIgv() {
		return montoIgv;
	}

	public void setMontoIgv(BigDecimal montoIgv) {
		this.montoIgv = montoIgv;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAdelanto() {
		return adelanto;
	}

	public void setAdelanto(BigDecimal adelanto) {
		this.adelanto = adelanto;
	}

	public BigDecimal getAdelantoPorc() {
		return adelantoPorc;
	}

	public void setAdelantoPorc(BigDecimal adelantoPorc) {
		this.adelantoPorc = adelantoPorc;
	}

	public BigDecimal getPagoPendiente() {
		return pagoPendiente;
	}

	public void setPagoPendiente(BigDecimal pagoPendiente) {
		this.pagoPendiente = pagoPendiente;
	}

	public Moneda getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(Moneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public Double getDescuentoTotal() {
		return descuentoTotal;
	}

	public void setDescuentoTotal(Double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public TablaAuxiliarDetalle getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TablaAuxiliarDetalle tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Integer getDiasPagoCredito() {
		return diasPagoCredito;
	}

	public void setDiasPagoCredito(Integer diasPagoCredito) {
		this.diasPagoCredito = diasPagoCredito;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public List<OrdenVentaDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<OrdenVentaDetalle> detalle) {
		this.detalle = detalle;
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

	public List<OrdenVentaDespacho> getDespacho() {
		return despacho;
	}

	public void setDespacho(List<OrdenVentaDespacho> despacho) {
		this.despacho = despacho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteContacto getContacto() {
		return contacto;
	}

	public void setContacto(ClienteContacto contacto) {
		this.contacto = contacto;
	}

	public String getNombreTrabajo() {
		return nombreTrabajo;
	}

	public void setNombreTrabajo(String nombreTrabajo) {
		this.nombreTrabajo = nombreTrabajo;
	}

	public TablaAuxiliarDetalle getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(TablaAuxiliarDetalle metodoPago) {
		this.metodoPago = metodoPago;
	}

	@PrePersist
	public void prePersist() {
		this.setFechaCrea(new Date());
	}

	private static final long serialVersionUID = 1L;
	
}