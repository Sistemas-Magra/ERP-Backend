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

import com.example.magra.erp.models.entity.maestro.CondicionReporte;
import com.example.magra.erp.models.service.maestros.ICondicionReporteService;

@RequestMapping("/api")
@RestController
public class CondicionRestController {
	@Autowired
	private ICondicionReporteService condicionService;
	
	@GetMapping("/condicion/all")
	public List<CondicionReporte> getAll(){ 
		return condicionService.getAllCondiciones();
	}
	
	@PostMapping("/condicion/create")
	public ResponseEntity<?> create(@RequestBody CondicionReporte condicion) {
		Map<String, Object> response = new HashMap<>();
		CondicionReporte newCondicion = null;
		
		try {
			newCondicion = condicionService.save(condicion);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al guardar el registro.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("condicion", newCondicion);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
