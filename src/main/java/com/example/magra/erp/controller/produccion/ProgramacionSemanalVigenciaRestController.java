package com.example.magra.erp.controller.produccion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanalVigencia;
import com.example.magra.erp.models.service.produccion.IProgramacionSemanalVigenciaService;

@RestController
@RequestMapping("/api")
public class ProgramacionSemanalVigenciaRestController {
	@Autowired
	private IProgramacionSemanalVigenciaService psvService;
	
	@GetMapping("/programacion-produccion/get-materiales-programados/{id}/{plantaId}")
	public List<Map<String, Object>> getMaterialesProgramados(@PathVariable Integer id, @PathVariable Integer plantaId) {
		return psvService.getMateriales(id, plantaId);
	}
	
	@GetMapping("/programacion-produccion/vigencia-detalle/{id}")
	public ProgramacionSemanalVigencia getById(@PathVariable Integer id) {
		return psvService.getById(id);
	}
	
	@PutMapping("/programacion-produccion/semanal/update")
	public ResponseEntity<?> update(@RequestBody ProgramacionSemanalVigencia psv, @Valid BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			psvService.save(psv);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/programacion-produccion/semanal")
	public ResponseEntity<?> create(@RequestBody ProgramacionSemanalVigencia psv, @Valid BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			psvService.save(psv);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}