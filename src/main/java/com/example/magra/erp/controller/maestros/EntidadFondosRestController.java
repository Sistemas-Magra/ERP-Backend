package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.EntidadFondos;
import com.example.magra.erp.models.service.maestros.IEntidadFondosService;

@RestController
@RequestMapping("/api")
public class EntidadFondosRestController {

	@Autowired
	private IEntidadFondosService entidadFondosService;
	
	@GetMapping("/entidad-fondos/all")
	public List<EntidadFondos> getAll() {
		return entidadFondosService.findAll();
	}
}
