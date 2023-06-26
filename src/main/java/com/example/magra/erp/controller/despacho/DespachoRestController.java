package com.example.magra.erp.controller.despacho;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.despacho.Despacho;
import com.example.magra.erp.models.service.despacho.IDespachoService;

@RestController
@RequestMapping("/api")
public class DespachoRestController {
	
	@Autowired
	private IDespachoService despachoService;
	
	@GetMapping("/despacho/find")
	public Despacho getByFecha(@RequestParam(value="f", required=true) Date fecha) {
		return despachoService.getDespachoByFecha(fecha);
	}
}