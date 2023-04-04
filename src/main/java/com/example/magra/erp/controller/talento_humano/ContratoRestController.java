package com.example.magra.erp.controller.talento_humano;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.talento_humano.Contrato;
import com.example.magra.erp.models.service.talento_humano.IContratoService;

@RequestMapping("/api")
@RestController
public class ContratoRestController {
	@Autowired
	private IContratoService contratoService;
	
	@GetMapping("/contrato/get-by-empleado/{empleadoId}")
	public Contrato getContratoByEmpleadoId(@PathVariable Integer empleadoId) {
		return contratoService.getContratoByEmpleado(empleadoId);
	}

	
	@GetMapping("/contrato/get-datos/{empleadoId}/{mes}/{anio}")
	public Map<String, Object> getDatosContrato(@PathVariable Integer empleadoId, @PathVariable Integer mes, @PathVariable Integer anio) {
		return contratoService.getDatosContrato(empleadoId, mes, anio);
	}
}