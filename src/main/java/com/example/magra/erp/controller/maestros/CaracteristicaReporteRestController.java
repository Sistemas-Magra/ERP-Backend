package com.example.magra.erp.controller.maestros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.CaracteristicaReporte;
import com.example.magra.erp.models.service.maestros.ICaracteristicaReporteService;

@RestController
@RequestMapping("/api")
public class CaracteristicaReporteRestController {
	@Autowired
	private ICaracteristicaReporteService caracteristicaService;
	
	@GetMapping("/caracteristica/all")
	public List<CaracteristicaReporte> getAll() {
		return caracteristicaService.getAllCaracterísticas();
	}
	
	@PostMapping("/caracteristica/create")
	public ResponseEntity<?> create(@RequestBody CaracteristicaReporte caracteristica) {
		Map<String, Object> response = new HashMap<>();
		CaracteristicaReporte newCaracteristica = null;
		
		try {
			newCaracteristica = caracteristicaService.save(caracteristica);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al guardar el registro.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("caracteristica", newCaracteristica);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
