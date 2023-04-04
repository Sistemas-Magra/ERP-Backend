package com.example.magra.erp.controller.talento_humano;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.talento_humano.IAsistenciaService;

@RestController
@RequestMapping("/api")
public class AsistenciaRestController {
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	@PostMapping("/asistencia/registrar")
	public ResponseEntity<?> registrarAsistencia(@RequestBody String json) {
		Map<String, Object> response = new HashMap<>();
		
		asistenciaService.registrarAsistencias(json);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
