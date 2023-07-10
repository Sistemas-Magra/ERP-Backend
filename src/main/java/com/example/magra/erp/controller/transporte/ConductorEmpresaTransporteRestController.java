package com.example.magra.erp.controller.transporte;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.transporte.IConductorEmpresaTransporteService;

@RestController
@RequestMapping("/api")
public class ConductorEmpresaTransporteRestController {
	@Autowired
	private IConductorEmpresaTransporteService conductorService;
	
	@GetMapping("/conductor/datos/{empresaTransporteId}/{nroDocumento}")
	public ResponseEntity<?> getDatosFromReniec(@PathVariable Integer empresaTransporteId, @PathVariable String nroDocumento){
		Map<String, Object> response = new HashMap<>();
		
		
		Integer id = conductorService.getIdByNroDocumentoAndEmpresaId(empresaTransporteId, nroDocumento);
		
		if(id != null) {
			response.put("mensaje", "El documento ingresado ya corresponde a un conductor en la empresa seleccionada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response = conductorService.getDatosFromSunat(nroDocumento);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}