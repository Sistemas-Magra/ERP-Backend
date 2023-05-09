package com.example.magra.erp.models.service.ventas;

import java.util.List;

import com.example.magra.erp.models.entity.ventas.Pago;

public interface IPagoService {
	Pago save(Pago pago);
	List<Pago> getPagosFromOrdenVenta(Integer id);
}