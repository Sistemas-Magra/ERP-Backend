package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Empleado;
import com.example.magra.erp.models.service.maestros.IEmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/empleado/autocomplete/{term}")
	public List<Empleado> autocomplete(@PathVariable String term) {
		return empleadoService.autocomplete(term);
	}
}
