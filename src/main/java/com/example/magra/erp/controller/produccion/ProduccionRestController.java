package com.example.magra.erp.controller.produccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.Produccion;
import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.produccion.IProduccionPlantaService;
import com.example.magra.erp.models.service.produccion.IProduccionService;

@RestController
@RequestMapping("/api")
public class ProduccionRestController {
	@Autowired
	private IProduccionService produccionService;
	
	@Autowired
	private IConfiguracionService configService;
	
	@Autowired
	private IProduccionPlantaService produccionPlantaService;
	
	@GetMapping("/produccion/listado-mensual/{indMes}")
	public Map<String, Object> getListadoInventarioMensual(@PathVariable Integer indMes) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("fijo", produccionService.getListadoOrdenesVentaByMes(indMes));
		response.put("diario", produccionService.getListadoDetalleOrdenesVentaByMes(indMes));
		response.put("anterior", produccionService.getStockMensual(indMes - 1));
		
		return response;
	}
	
	@GetMapping("/produccion/listado")
	public List<Map<String, Object>> getListado(
			@RequestParam(value="fd", required=false) String fechaDesde,
			@RequestParam(value="fh", required=false) String fechaHasta,
			@RequestParam(value="eid", required=false) Integer estadoId
		) {
		return produccionService.getListadoProduccion(fechaDesde, fechaHasta, estadoId);
	}
	
	@PutMapping("/produccion/update/listado-produccion/{stickerProduccion}")
	public ResponseEntity<?> update(@PathVariable String stickerProduccion, @RequestBody ProduccionPlanta produccionPlanta, @Valid BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			ProduccionPlanta prodNew = produccionPlantaService.save(produccionPlanta);
			produccionPlantaService.actualizarProduccion(stickerProduccion);
			response.put("object", prodNew);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al registrar la producción en planta por parte del servidor.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response.put("mensaje", "Producción en planta registrada correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/produccion/create")
	public ResponseEntity<?> create(@RequestBody ProduccionPlanta produccionPlanta, @Valid BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Integer idProduccion = produccionService.getIdByFecha();
		Produccion produccion;
		List<ProduccionPlanta> detalle;
		
		if(idProduccion == null) {
			produccion = new Produccion();
			produccion.setFecha(new Date());
			detalle = new ArrayList<>();
			detalle.add(produccionPlanta);
		} else {
			produccion = produccionService.getById(idProduccion);
			detalle = produccion.getDetallePlanta();
			
			Boolean already = false;
			
			for(ProduccionPlanta pp: detalle) {
				if(pp.getPlanta().getId() == produccionPlanta.getPlanta().getId()) {
					already = true;
					break;
				}
			}
			
			if(!already) {
				detalle.add(produccionPlanta);
			}
		}
		
		produccion.setDetallePlanta(detalle);
		produccion.setEstado(configService.findById(1, "ESTPRD"));
		
		try {
			Produccion produccionNew = produccionService.save(produccion);

			ProduccionPlanta prodRegistered = new ProduccionPlanta();
			
			for(ProduccionPlanta prod: produccionNew.getDetallePlanta()) {
				if(prod.getPlanta().getId() == produccionPlanta.getPlanta().getId()) {
					prodRegistered = prod;
					break;
				}
			}
			
			response.put("object", prodRegistered);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al registrar la producción en planta por parte del servidor.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response.put("mensaje", "Producción en planta registrada correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
}