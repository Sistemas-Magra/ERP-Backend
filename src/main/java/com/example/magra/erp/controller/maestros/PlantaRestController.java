package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Planta;
import com.example.magra.erp.models.service.maestros.IPlantaService;

@RestController
@RequestMapping("/api")
public class PlantaRestController {
	@Autowired
	private IPlantaService plantaService;
	
	@GetMapping("/planta/get-activos")
	public List<Planta> getPlantasActivas() {
		return plantaService.getPlantasActivas();
	}
}
