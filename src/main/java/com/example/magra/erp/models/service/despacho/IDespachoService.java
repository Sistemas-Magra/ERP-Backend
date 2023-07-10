package com.example.magra.erp.models.service.despacho;

import java.util.Date;

import com.example.magra.erp.models.entity.despacho.Despacho;

public interface IDespachoService {
	Despacho getById(Integer id);
	Despacho getDespachoByFecha(Date fecha);
	Despacho save(Despacho despacho);
}