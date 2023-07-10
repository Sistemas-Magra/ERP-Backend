package com.example.magra.erp.controller.transporte;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;
import com.example.magra.erp.models.service.transporte.IEmpresaTransporteService;

@RestController
@RequestMapping("/api")
public class EmpresaTransporteRestController {
	@Autowired
	private IEmpresaTransporteService etService;
	
	@GetMapping("/empresa-transporte/sunat/{nroDocumento}")
	public ResponseEntity<?> getFromSunat(@PathVariable String nroDocumento) {
		Map<String, Object> response = etService.getDatosFromSunat(nroDocumento);
		
		if(response.get("mensaje") != null) {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/empresa-transporte/all")
	public List<EmpresaTransporte> getAll() {
		return etService.getAll();
	}
	
	@PostMapping("/empresa-transporte/create-update")
	public EmpresaTransporte create(@RequestBody EmpresaTransporte empresa) {
		return etService.save(empresa);
	}
}