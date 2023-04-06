package com.example.magra.erp.controller.sig;

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

import com.example.magra.erp.models.entity.sig.ReporteActosCondiciones;
import com.example.magra.erp.models.service.sig.IReporteActoService;

@RestController
@RequestMapping("/api")
public class ReporteActosRestController {
	@Autowired
	private IReporteActoService reporteService;
	
	@GetMapping("/reporte-acto/listado")
	public List<Map<String, Object>> getListado(
				@RequestParam(value="fecha", required=false) String fecha,
				@RequestParam(value="plantaId", required=false) Integer plantaId,
				@RequestParam(value="estadoId", required=false) Integer estadoId
			) {
		return reporteService.getlistadoReporte(fecha, plantaId, estadoId);
	}
	
	@PostMapping("/reporte-acto/create")
	public ResponseEntity<?> create(@RequestBody ReporteActosCondiciones reporte) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			reporteService.save(reporte);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al querer registrar los datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
