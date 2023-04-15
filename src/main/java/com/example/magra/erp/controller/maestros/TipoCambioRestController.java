package com.example.magra.erp.controller.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.TipoCambio;
import com.example.magra.erp.models.service.maestros.ITipoCambioService;

@RestController
@RequestMapping("/api")
public class TipoCambioRestController {
	@Autowired
	private ITipoCambioService tipoCambioService;
	
	@GetMapping("/tipo-cambio/last")
	public TipoCambio getUltimoTipoCambio() {
		return tipoCambioService.getUltimoTipoCambio();
	}
}
