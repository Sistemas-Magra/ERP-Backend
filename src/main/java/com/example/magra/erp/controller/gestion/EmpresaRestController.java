package com.example.magra.erp.controller.gestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.gestion.Empresa;
import com.example.magra.erp.models.service.gestion.IEmpresaService;

@RestController
@RequestMapping("/api")
public class EmpresaRestController {
	@Autowired
	private IEmpresaService empresaService;
	
	@GetMapping("/empresa/activos")
	private List<Empresa> getEmpresasActivas(){
		return empresaService.getEmpresasActivas();
	}
}
