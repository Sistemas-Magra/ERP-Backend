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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;
import com.example.magra.erp.models.service.produccion.IProtocoloPruebaCalidadService;

@RestController
@RequestMapping("/api")
public class ProtocoloPruebaCalidadRestController {
	@Autowired
	private IProtocoloPruebaCalidadService protocoloService;
	
	@GetMapping("/protocolo/get/{id}")
	public ProtocoloPruebaCalidad getById(@PathVariable Integer id) {
		return protocoloService.getProtocoloById(id);
	}
	
	@GetMapping("/protocolo/get-listado")
	public List<Map<String, Object>> listado(
				@RequestParam(value="cl", required=false, defaultValue="") String cliente,
				@RequestParam(value="ot", required=false, defaultValue="") String ordenTrabajo,
				@RequestParam(value="pr", required=false, defaultValue="") String producto,
				@RequestParam(value="f", required=false) String fecha 
			) {
		return protocoloService.getListado(cliente, ordenTrabajo, producto, fecha);
	}
	
	@PutMapping("/protocolo/update")
	public ResponseEntity<?> update(@RequestBody ProtocoloPruebaCalidad protocolo, @Valid BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			protocoloService.save(protocolo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/protocolo/create")
	public ResponseEntity<?> create(@RequestBody ProtocoloPruebaCalidad protocolo, @Valid BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			protocoloService.save(protocolo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}