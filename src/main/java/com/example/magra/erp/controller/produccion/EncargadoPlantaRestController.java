package com.example.magra.erp.controller.produccion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.produccion.IEncargadoPlantaService;

@RestController
@RequestMapping("/api")
public class EncargadoPlantaRestController {
	@Autowired
	private IEncargadoPlantaService encargadoPlantaService;
	
	@GetMapping("/encargado-planta/all-hoy")
	public List<Map<String, Object>> getEncargadosPlantasHoy(){
		return encargadoPlantaService.getEncargadosAsignados();
	}
	
	@GetMapping("/encargado-planta/valid/{plantaId}")
	public List<Map<String, Object>> getEncargadosPlantasPorPlanta(@PathVariable Integer plantaId){
		return encargadoPlantaService.getEncargadosAsignadosPorPlanta(plantaId);
	}
	
	@PostMapping("/encargado-planta/create/{idUsuario}/{plantaId}")
	public ResponseEntity<?> create(@PathVariable Integer idUsuario, @PathVariable Integer plantaId) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			encargadoPlantaService.insertEncargado(idUsuario, plantaId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}