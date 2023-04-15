package com.example.magra.erp.controller.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.ProductoVenta;
import com.example.magra.erp.models.service.maestros.IProductoVentaService;

@RestController
@RequestMapping("/api")
public class ProductoVentaRestController {
	@Autowired
	private IProductoVentaService productoVentaService;
	
	@GetMapping("/producto-venta/autocomplete/{term}")
	public List<ProductoVenta> autocomplete(@PathVariable String term) {
		return productoVentaService.autocomplete(term);
	}
}