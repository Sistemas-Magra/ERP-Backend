package com.example.magra.erp.controller.ventas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.entity.maestro.ClienteContacto;
import com.example.magra.erp.models.entity.ventas.OrdenVenta;
import com.example.magra.erp.models.service.maestros.IClienteContactoService;
import com.example.magra.erp.models.service.maestros.IClienteService;
import com.example.magra.erp.models.service.ventas.IOrdenVentaService;

@RestController
@RequestMapping("/api")
public class OrdenVentaRestController {
	@Autowired
	private IOrdenVentaService ordenVentaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IClienteContactoService contactoService;
	
	@GetMapping("/orden-venta/listado")
	public List<Map<String, Object>> getListadoMaestro(
				@RequestParam(value="cli", required=false) String cliente,
				@RequestParam(value="fdes", required=false) String fechaDesde,
				@RequestParam(value="fhas", required=false) String fechaHasta,
				@RequestParam(value="anu", required=false) Integer indVerAnulados
			) {
		return ordenVentaService.getListadoMaestro(cliente, fechaDesde, fechaHasta, indVerAnulados);
	}
	
	
	@PostMapping("/orden-venta/create")
	public ResponseEntity<?> create(@RequestBody OrdenVenta ordenVenta){
		Map<String, Object> response = new HashMap<>();
		
		try {
			Cliente cliente = clienteService.save(ordenVenta.getCliente());
			ordenVenta.setCliente(cliente);
			ClienteContacto contacto = contactoService.findByCorreo(ordenVenta.getContacto().getCorreo());
			ordenVenta.setContacto(contacto);
			ordenVenta.setCodigo("C" + String.format("%06d", ordenVentaService.getCodigo()));
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