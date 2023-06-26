package com.example.magra.erp.controller.transporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;
import com.example.magra.erp.models.service.transporte.IEmpresaTransporteService;

@RestController
@RequestMapping("/api")
public class EmpresaTransporteRestController {
	@Autowired
	private IEmpresaTransporteService etService;
	
	@GetMapping("/empresa-transporte/all")
	public List<EmpresaTransporte> getAll() {
		return etService.getAll();
	}
}