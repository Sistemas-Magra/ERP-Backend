package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Banco;
import com.example.magra.erp.models.service.maestros.IBancoService;

@RestController
@RequestMapping("/api")
public class BancoRestController {
	
	@Autowired
	private IBancoService bancoService;
	
	@GetMapping("/banco/get-activos")
	public List<Banco> getBancosActivos() {
		return bancoService.getBancosActivos();
	}

}
