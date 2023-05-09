package com.example.magra.erp.models.entity.ventas;

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
@Table(name="ven_pago")
public class Pago implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TablaAuxiliarDetalle tipoPago;

	@Column(precision =10, scale=2)	
	private BigDecimal monto;

	@Column(precision =10, scale=2)	
	private BigDecimal diferenciaTotal;
	
	private Boolean indEsAdelanto;

	private Integer idUsuarioCrea;
	
	@Column(length=150)
	private String nombreUsuarioCrea;
	
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

	public TablaAuxiliarDetalle getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TablaAuxiliarDetalle tipoPago) {
		this.tipoPago = tipoPago;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getDiferenciaTotal() {
		return diferenciaTotal;
	}

	public void setDiferenciaTotal(BigDecimal diferenciaTotal) {
		this.diferenciaTotal = diferenciaTotal;
	}

	public Boolean getIndEsAdelanto() {
		return indEsAdelanto;
	}

	public void setIndEsAdelanto(Boolean indEsAdelanto) {
		this.indEsAdelanto = indEsAdelanto;
	}

	public Integer getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public String getNombreUsuarioCrea() {
		return nombreUsuarioCrea;
	}

	public void setNombreUsuarioCrea(String nombreUsuarioCrea) {
		this.nombreUsuarioCrea = nombreUsuarioCrea;
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