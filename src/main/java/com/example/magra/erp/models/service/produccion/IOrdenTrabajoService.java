package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;

public interface IOrdenTrabajoService {
	OrdenTrabajo getById(Integer id);
	OrdenTrabajo save(OrdenTrabajo ot);
	Integer getCatOT();
	List<OrdenTrabajo> autocomplete(String term);
	List<OrdenTrabajo> autocompletePedido(String term);
	List<Map<String, Object>> getListadoOrdenesTrabajo();
	List<Map<String, Object>> getDetalleOrdenTrabajo(Integer ordenTrabajoId);	
	List<Map<String, Object>> getProductosFromOrdenTrabajo(Integer id);
}