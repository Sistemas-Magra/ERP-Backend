package com.example.magra.erp.controller.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanal;
import com.example.magra.erp.models.service.produccion.IProgramacionSemanalService;

@RestController
@RequestMapping("/api")
public class ProgramacionSemanalRestController {
	@Autowired
	private IProgramacionSemanalService psService;
	
	@GetMapping("/programacion-produccion/versiones/{progVigId}")
	public List<Map<String, Object>> getListadoVersiones(@PathVariable Integer progVigId) {
		return psService.getListadoVersiones(progVigId);
	}
	
	@GetMapping("/programacion-produccion/listado")
	public List<Map<String, Object>> getListadoMaestro(
			@RequestParam(value="anio", required=false) Integer anio, 
			@RequestParam(value="mes", required=false) Integer mes) {
		return psService.getListadoProgramaciones(anio, mes);
	}
	
	@GetMapping("/programacion-produccion/detalle-semanal/{id}")
	public ProgramacionSemanal getDetalle(@PathVariable Integer id) {
		return psService.getById(id);
	}
}
