package com.example.magra.erp.models.service.produccion;

import java.util.List;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;

public interface IOrdenTrabajoService {
	OrdenTrabajo save(OrdenTrabajo ot);
	Integer getCatOT();
	List<OrdenTrabajo> autocomplete(String term);
}