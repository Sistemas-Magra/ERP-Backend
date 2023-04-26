package com.example.magra.erp.controller.produccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;
import com.example.magra.erp.models.service.produccion.IOrdenTrabajoService;

@RestController
@RequestMapping("/api")
public class OrdenTrabajoRestController {
	@Autowired
	private IOrdenTrabajoService otService;
	
	@GetMapping("/orden-trabajo/autocomplete/{term}")
	public List<OrdenTrabajo> autocomplete(@PathVariable String term) {
		return otService.autocomplete(term);
	}
}