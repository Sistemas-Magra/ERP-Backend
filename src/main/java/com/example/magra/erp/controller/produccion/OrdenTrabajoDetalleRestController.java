package com.example.magra.erp.controller.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.produccion.IOrdenTrabajoDetalleService;

@RestController
@RequestMapping("/api")
public class OrdenTrabajoDetalleRestController {
	@Autowired
	private IOrdenTrabajoDetalleService otdService;
	
	@GetMapping("/orden-trabajo-detalle/get-pendientes/{ordenVentaId}")
	public List<Map<String, Object>> getPendientes(@PathVariable Integer ordenVentaId) {
		return otdService.getPendientes(ordenVentaId);
	}
}