package com.example.magra.erp.controller.produccion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.example.magra.erp.helper.ZipUtil;
import com.example.magra.erp.models.entity.produccion.Produccion;
import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;
import com.example.magra.erp.models.service.IUploadFileService;
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
	
	@Autowired
	private IUploadFileService uploadService;
	
	@GetMapping("/produccion/descargar-protocolos/{ordenVentaId}")
	public ResponseEntity<Resource> descargarControlCalidad(@PathVariable Integer ordenVentaId) {
		
		Resource recurso = null;
		
		try {
			produccionService.generarProtocolo(ordenVentaId);
			recurso = uploadService.cargar("archivo.xlsx", System.getProperty("user.home"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@PostMapping("/produccion/descargar-control-calidad/{ordenTrabajoId}/{sedeId}")
    public ResponseEntity<Resource> descargarControlCalidad(
    			@PathVariable Integer ordenTrabajoId,
    			@PathVariable Integer sedeId,
    			@RequestParam(value="fecha", required=true) String fecha,
    			@RequestBody List<Map<String, Object>> detalle
    		) {
		System.out.println(fecha);
		Resource recurso = null;
		try {
			produccionService.generarCartaCalidad(ordenTrabajoId, sedeId, fecha, detalle);
			recurso = uploadService.cargar("archivo3.docx", System.getProperty("user.home"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
	
	@GetMapping("/produccion/descargar-carta-garantia/{ordenTrabajoId}/{sedeId}")
    public ResponseEntity<Resource> descargarCartaGarantia(@PathVariable Integer ordenTrabajoId, @PathVariable Integer sedeId) {
		Resource recurso = null;
		try {
			produccionService.generarCartaGarantia(ordenTrabajoId, sedeId, 1);
			produccionService.generarCartaGarantia(ordenTrabajoId, sedeId, 2);
			
			String zipFilePath = System.getProperty("user.home") + File.separator + "comprimido.zip";
			String file1Path = System.getProperty("user.home") + File.separator + "archivo1.docx";
			String file2Path = System.getProperty("user.home") + File.separator + "archivo2.docx";

			ZipUtil zipUtil = new ZipUtil();
			zipUtil.zipFiles(zipFilePath, file1Path, file2Path);
			
			recurso = uploadService.cargar("comprimido.zip", System.getProperty("user.home"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
	
	@PostMapping("/produccion/descargar-acta-conformidad/{ordenTrabajoId}/{sedeId}")
    public ResponseEntity<Resource> descargarActaConformidad(
			@PathVariable Integer ordenTrabajoId,
			@PathVariable Integer sedeId,
			@RequestParam(value="fechaInicio", required=true) String fechaInicio,
			@RequestParam(value="fechaFin", required=true) String fechaFin,
			@RequestBody List<Map<String, Object>> detalle
		) {
		Resource recurso = null;
		try {
			produccionService.generarActaConformidad(ordenTrabajoId, sedeId, fechaInicio, fechaFin, detalle);
			recurso = uploadService.cargar("archivo.docx", System.getProperty("user.home"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
	
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
	
	@PutMapping("/produccion/update/calidad")
	public ResponseEntity<?> updateCalidad(@RequestBody ProduccionPlanta produccionPlanta, @Valid BindingResult result) {
		
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
			produccionPlantaService.actualizarCalidad();
			response.put("object", prodNew);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al registrar la producción en planta por parte del servidor.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response.put("mensaje", "Producción en planta registrada correctamente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
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
	
	@GetMapping("/produccion/get/{plantaId}/{produccionId}")
	public ResponseEntity<?> getProduccionPlantaByPlantaIdProduccionId(
					@PathVariable Integer plantaId, 
					@PathVariable Integer produccionId
				) {
		Map<String, Object> response = new HashMap<>();
		
		Integer ppId = produccionPlantaService.getIdByProduccionAndPlanta(plantaId, produccionId);
		
		if(ppId == null) {
			response.put("mensaje", "No hay producción registrada en esa planta en el día seleccionado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		ProduccionPlanta pp = produccionPlantaService.getById(ppId);
		
		pp.setIndProcesoCalidad(true);
		pp = produccionPlantaService.save(pp);

		response.put("obj", pp);
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