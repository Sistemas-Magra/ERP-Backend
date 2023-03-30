package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Cargo;
import com.example.magra.erp.models.service.maestros.ICargoService;

@RestController
@RequestMapping("/api")
public class CargoRestController {
	@Autowired
	private ICargoService cargoService;
	
	@GetMapping("/cargo/all")
	public List<Cargo> getAll() {
		return cargoService.findAll();
	}
}
