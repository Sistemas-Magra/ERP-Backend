package com.example.magra.erp.controller.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.ventas.Pago;
import com.example.magra.erp.models.service.ventas.IPagoService;

@RestController
@RequestMapping("/api")
public class PagoRestController {
	@Autowired
	private IPagoService pagoService;
	
	@GetMapping("/pagos/get-by-ovid/{ordenVentaId}")
	public List<Pago> getPagosByOrdenVentaId(@PathVariable Integer ordenVentaId) {
		return pagoService.getPagosFromOrdenVenta(ordenVentaId);
	}
}