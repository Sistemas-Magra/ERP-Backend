package com.example.magra.erp.controller.auxiliar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.auxiliares.Parametro;
import com.example.magra.erp.models.service.auxiliares.IParametroService;

@RequestMapping("/api")
@RestController
public class ParametroRestController {
	@Autowired
	private IParametroService parametroService;
	
	@GetMapping("/parametro/get-by-id/{id}")
	public Parametro getParametroById(Integer id) {
		return parametroService.getParametroById(id);
	}
}
