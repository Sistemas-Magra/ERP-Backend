package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Moneda;
import com.example.magra.erp.models.service.maestros.IMonedaService;

@RestController
@RequestMapping("/api")
public class MonedaRestController {
	@Autowired
	private IMonedaService monedaService;
	
	@GetMapping("/moneda/all")
	public List<Moneda> getAllMonedas(){
		return monedaService.getAll();
	}
}
