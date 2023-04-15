package com.example.magra.erp.controller.ventas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.ventas.OrdenVenta;
import com.example.magra.erp.models.service.ventas.IOrdenVentaService;

@RestController
@RequestMapping("/api")
public class OrdenVentaRestController {
	@Autowired
	private IOrdenVentaService ordenVentaService;
	
	@PostMapping("/orden-venta/create")
	public ResponseEntity<?> create(@RequestBody OrdenVenta ordenVenta){
		Map<String, Object> response = new HashMap<>();
		
		try {
			ordenVentaService.save(ordenVenta);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al registrar cotización.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response.put("mensaje", "Cotización registrada correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}