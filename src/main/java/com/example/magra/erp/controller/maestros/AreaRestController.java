package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Area;
import com.example.magra.erp.models.service.maestros.IAreaService;

@RestController
@RequestMapping("/api")
public class AreaRestController {
	@Autowired
	private IAreaService areaService;
	
	@GetMapping("/area/all")
	public List<Area> getAll() {
		return areaService.findAll();
	}
}
