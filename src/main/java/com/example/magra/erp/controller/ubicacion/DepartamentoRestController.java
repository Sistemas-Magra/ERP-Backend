package com.example.magra.erp.controller.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.ubicacion.Departamento;
import com.example.magra.erp.models.service.ubicacion.IDepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoRestController {
	@Autowired
	private IDepartamentoService departamentoService;
	
	@GetMapping("/departamento/all")
	public List<Departamento> getAll() {
		return departamentoService.findAll();
	}
}