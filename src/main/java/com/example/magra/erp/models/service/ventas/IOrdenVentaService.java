package com.example.magra.erp.models.service.ventas;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.ventas.OrdenVenta;

public interface IOrdenVentaService {
	OrdenVenta save(OrdenVenta ordenVenta);
	List<Map<String, Object>> getListadoMaestro(String cliente, String fechaDesde, String fechaHasta, Integer indVerAnulados);
	Integer getCodigo();
}