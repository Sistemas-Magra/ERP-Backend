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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.produccion.Produccion;
import com.example.magra.erp.models.entity.produccion.ProduccionAccesorioRegistroAcabado;
import com.example.magra.erp.models.entity.produccion.ProduccionAccesorioRegistroArmado;
import com.example.magra.erp.models.entity.produccion.ProduccionAccesorioRegistroMezcla;
import com.example.magra.erp.models.entity.produccion.ProduccionAccesorioRegistroVibracion;
import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroCentrifugado;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroCurado;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroDesencrofado;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroEstructura;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroMezcla;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroTubosPines;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.maestros.IPlantaService;
import com.example.magra.erp.models.service.produccion.IProduccionPlantaService;
import com.example.magra.erp.models.service.produccion.IProduccionService;

@RestController
@RequestMapping("/api")
public class FormatosRestController {
	
	@Autowired
	private IProduccionService prodService;
	
	@Autowired
	private IProduccionPlantaService prodPlantaService;
	
	@Autowired
	private IConfiguracionService confService;
	
	@Autowired
	private IPlantaService plantaService;
	
	@GetMapping("/formatos/get-listado/{prodId}/{plantaId}/{ind}")
	public Map<String, Object> getListadoFormatoVista(@PathVariable Integer prodId, @PathVariable Integer plantaId, @PathVariable Integer ind) {
		Map<String, Object> response = new HashMap<>();
		
		if(prodId == null) {
			response.put("mensaje", "No hay registros de producción en esta planta.");
			return response;
		}
		
		Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
		
		if(prodPlantaId == null) {
			response.put("mensaje", "No hay registros de producción en esta planta.");
			return response;
		}
		
		ProduccionPlanta prodPlanta = prodPlantaService.getById(prodPlantaId);
		
		switch (ind){
		
			//Postes
			case 1: {
				response.put("listado", prodPlanta.getDetalleMezcla());
				System.out.println(prodPlanta.getId());
				break;
			}
			case 2: {
				response.put("listado", prodPlanta.getDetalleEstructura());
				break;
			}
			case 3: {
				response.put("listado", prodPlanta.getDetalleTubosPines());
				break;
			}
			case 4: {
				response.put("listado", prodPlanta.getDetalleCentrifugado());
				break;
			}
			case 5: {
				response.put("listado", prodPlanta.getDetalleDesencrofado());
				break;
			}
			case 6: {
				response.put("listado", prodPlanta.getDetalleCurado());
				break;
			}
			
			//Accesorios
			case 7: {
				response.put("listado", prodPlanta.getDetalleMezclaAccesorios());
				break;
			}
			case 8: {
				response.put("listado", prodPlanta.getDetalleArmadoAccesorios());
				break;
			}
			case 9: {
				response.put("listado", prodPlanta.getDetalleVibracionAccesorios());
				break;
			}
			case 10: {
				response.put("listado", prodPlanta.getDetalleAcabadoAccesorios());
				break;
			}
		}
		
		return response;
	}
	
	@GetMapping("/formatos/get-listado/{plantaId}/{ind}")
	public Map<String, Object> getListadoFormato(@PathVariable Integer plantaId, @PathVariable Integer ind) {
		Map<String, Object> response = new HashMap<>();

		Integer prodId = prodService.getIdByFecha();
		
		if(prodId == null) {
			response.put("mensaje", "No hay registros de producción en esta planta.");
			return response;
		}
		
		Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
		
		if(prodPlantaId == null) {
			response.put("mensaje", "No hay registros de producción en esta planta.");
			return response;
		}
		
		ProduccionPlanta prodPlanta = prodPlantaService.getById(prodPlantaId);
		
		switch (ind){
		
			//Postes
			case 1: {
				response.put("listado", prodPlanta.getDetalleMezcla());
				System.out.println(prodPlanta.getId());
				break;
			}
			case 2: {
				response.put("listado", prodPlanta.getDetalleEstructura());
				break;
			}
			case 3: {
				response.put("listado", prodPlanta.getDetalleTubosPines());
				break;
			}
			case 4: {
				response.put("listado", prodPlanta.getDetalleCentrifugado());
				break;
			}
			case 5: {
				response.put("listado", prodPlanta.getDetalleDesencrofado());
				break;
			}
			case 6: {
				response.put("listado", prodPlanta.getDetalleCurado());
				break;
			}
			
			//Accesorios
			case 7: {
				response.put("listado", prodPlanta.getDetalleMezclaAccesorios());
				break;
			}
			case 8: {
				response.put("listado", prodPlanta.getDetalleArmadoAccesorios());
				break;
			}
			case 9: {
				response.put("listado", prodPlanta.getDetalleVibracionAccesorios());
				break;
			}
			case 10: {
				response.put("listado", prodPlanta.getDetalleAcabadoAccesorios());
				break;
			}
		}
		
		return response;
	}
	
	@PostMapping("/formatos/accesorios/acabado/create/{plantaId}")
	public ResponseEntity<?> createVibracionAccesorios(@PathVariable Integer plantaId, @RequestBody ProduccionAccesorioRegistroAcabado acabado, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionAccesorioRegistroAcabado> listAcabado = new ArrayList<>();
			listAcabado.add(acabado);
			
			prodPlanta.setDetalleAcabadoAccesorios(listAcabado);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionAccesorioRegistroAcabado> listAcabado = new ArrayList<>();
				listAcabado.add(acabado);
				
				prodPlanta.setDetalleAcabadoAccesorios(listAcabado);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionAccesorioRegistroAcabado> listAcabado = prodPlanta.getDetalleAcabadoAccesorios();
				listAcabado.add(acabado);
				
				prodPlanta.setDetalleAcabadoAccesorios(listAcabado);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/accesorios/vibracion/create/{plantaId}")
	public ResponseEntity<?> createVibracionAccesorios(@PathVariable Integer plantaId, @RequestBody ProduccionAccesorioRegistroVibracion vibracion, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionAccesorioRegistroVibracion> listVibracion = new ArrayList<>();
			listVibracion.add(vibracion);
			
			prodPlanta.setDetalleVibracionAccesorios(listVibracion);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionAccesorioRegistroVibracion> listVibracion = new ArrayList<>();
				listVibracion.add(vibracion);
				
				prodPlanta.setDetalleVibracionAccesorios(listVibracion);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionAccesorioRegistroVibracion> listVibracion = prodPlanta.getDetalleVibracionAccesorios();
				listVibracion.add(vibracion);
				
				prodPlanta.setDetalleVibracionAccesorios(listVibracion);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/accesorios/armado/create/{plantaId}")
	public ResponseEntity<?> createArmadoAccesorios(@PathVariable Integer plantaId, @RequestBody ProduccionAccesorioRegistroArmado armado, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionAccesorioRegistroArmado> listArmado = new ArrayList<>();
			listArmado.add(armado);
			
			prodPlanta.setDetalleArmadoAccesorios(listArmado);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionAccesorioRegistroArmado> listArmado = new ArrayList<>();
				listArmado.add(armado);
				
				prodPlanta.setDetalleArmadoAccesorios(listArmado);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionAccesorioRegistroArmado> listArmado = prodPlanta.getDetalleArmadoAccesorios();
				listArmado.add(armado);
				
				prodPlanta.setDetalleArmadoAccesorios(listArmado);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/accesorios/mezcla/create/{plantaId}")
	public ResponseEntity<?> createMezclaAccesorios(@PathVariable Integer plantaId, @RequestBody ProduccionAccesorioRegistroMezcla mezcla, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionAccesorioRegistroMezcla> listMezcla = new ArrayList<>();
			listMezcla.add(mezcla);
			
			prodPlanta.setDetalleMezclaAccesorios(listMezcla);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionAccesorioRegistroMezcla> listMezcla = new ArrayList<>();
				listMezcla.add(mezcla);
				
				prodPlanta.setDetalleMezclaAccesorios(listMezcla);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionAccesorioRegistroMezcla> listMezcla = prodPlanta.getDetalleMezclaAccesorios();
				listMezcla.add(mezcla);
				
				prodPlanta.setDetalleMezclaAccesorios(listMezcla);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/curado/create/{plantaId}")
	public ResponseEntity<?> createCurado(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroCurado curado, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroCurado> listCurado = new ArrayList<>();
			listCurado.add(curado);
			
			prodPlanta.setDetalleCurado(listCurado);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroCurado> listCurado = new ArrayList<>();
				listCurado.add(curado);
				
				prodPlanta.setDetalleCurado(listCurado);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionRegistroCurado> listCurado = prodPlanta.getDetalleCurado();
				listCurado.add(curado);
				
				prodPlanta.setDetalleCurado(listCurado);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/desencrofado/create/{plantaId}")
	public ResponseEntity<?> createDesencrofado(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroDesencrofado desencrofado, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroDesencrofado> listDesencrofado = new ArrayList<>();
			listDesencrofado.add(desencrofado);
			
			prodPlanta.setDetalleDesencrofado(listDesencrofado);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroDesencrofado> listDesencrofado = new ArrayList<>();
				listDesencrofado.add(desencrofado);
				
				prodPlanta.setDetalleDesencrofado(listDesencrofado);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionRegistroDesencrofado> listDesencrofado = prodPlanta.getDetalleDesencrofado();
				listDesencrofado.add(desencrofado);
				
				prodPlanta.setDetalleDesencrofado(listDesencrofado);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/centrifugado/create/{plantaId}")
	public ResponseEntity<?> createCentrifugado(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroCentrifugado centrifugado, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroCentrifugado> listCentrifugado = new ArrayList<>();
			listCentrifugado.add(centrifugado);
			
			prodPlanta.setDetalleCentrifugado(listCentrifugado);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroCentrifugado> listCentrifugado = new ArrayList<>();
				listCentrifugado.add(centrifugado);
				
				prodPlanta.setDetalleCentrifugado(listCentrifugado);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);
				
				List<ProduccionRegistroCentrifugado> listCentrifugado = prodPlanta.getDetalleCentrifugado();
				listCentrifugado.add(centrifugado);
				
				prodPlanta.setDetalleCentrifugado(listCentrifugado);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/tubos-pines/create/{plantaId}")
	public ResponseEntity<?> createTubosPines(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroTubosPines tubosPines, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroTubosPines> listTubosPines = new ArrayList<>();
			listTubosPines.add(tubosPines);
			
			prodPlanta.setDetalleTubosPines(listTubosPines);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroTubosPines> listTubosPines = new ArrayList<>();
				listTubosPines.add(tubosPines);
				
				prodPlanta.setDetalleTubosPines(listTubosPines);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);

				List<ProduccionRegistroTubosPines> listTubosPines = prodPlanta.getDetalleTubosPines();
				listTubosPines.add(tubosPines);
				
				prodPlanta.setDetalleTubosPines(listTubosPines);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/estructura/create/{plantaId}")
	public ResponseEntity<?> createEstructura(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroEstructura estructura, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroEstructura> listEstructura = new ArrayList<>();
			listEstructura.add(estructura);
			
			prodPlanta.setDetalleEstructura(listEstructura);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroEstructura> listEstructura = new ArrayList<>();
				listEstructura.add(estructura);
				
				prodPlanta.setDetalleEstructura(listEstructura);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);

				List<ProduccionRegistroEstructura> listEstructura = prodPlanta.getDetalleEstructura();
				listEstructura.add(estructura);
				
				prodPlanta.setDetalleEstructura(listEstructura);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formatos/postes/mezcla/create/{plantaId}")
	public ResponseEntity<?> createMezcla(@PathVariable Integer plantaId, @RequestBody ProduccionRegistroMezcla mezcla, @Valid BindingResult result){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Produccion produccion;
		Integer prodId = prodService.getIdByFecha();
		ProduccionPlanta prodPlanta;
		
		if(prodId == null) {
			produccion = new Produccion();
			produccion.setEstado(confService.findById(1, "ESTPRD"));
			produccion.setFecha(new Date());
			
			prodPlanta = new ProduccionPlanta();
			prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
			prodPlanta.setIndProcesoCalidad(false);
			prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
			
			List<ProduccionRegistroMezcla> listMezcla = new ArrayList<>();
			listMezcla.add(mezcla);
			
			prodPlanta.setDetalleMezcla(listMezcla);
			
			List<ProduccionPlanta> listPlanta = new ArrayList<>();
			listPlanta.add(prodPlanta);
			
			produccion.setDetallePlanta(listPlanta);
			
			produccion = prodService.save(produccion);
			
		} else {
			Integer prodPlantaId = prodPlantaService.getIdByProduccionAndPlanta(plantaId, prodId);
			
			if(prodPlantaId == null) {
				produccion = prodService.getById(prodId);
				
				prodPlanta = new ProduccionPlanta();
				prodPlanta.setEstado(confService.findById(1, "ESTPPL"));
				prodPlanta.setIndProcesoCalidad(false);
				prodPlanta.setPlanta(plantaService.getPlantaById(plantaId));
				
				List<ProduccionRegistroMezcla> listMezcla = new ArrayList<>();
				listMezcla.add(mezcla);
				
				prodPlanta.setDetalleMezcla(listMezcla);
				
				List<ProduccionPlanta> listPlanta = produccion.getDetallePlanta();
				listPlanta.add(prodPlanta);
				
				produccion.setDetallePlanta(listPlanta);
				
				produccion = prodService.save(produccion);
			} else {
				prodPlanta = prodPlantaService.getById(prodPlantaId);

				List<ProduccionRegistroMezcla> listMezcla = prodPlanta.getDetalleMezcla();
				listMezcla.add(mezcla);
				
				prodPlanta.setDetalleMezcla(listMezcla);
				
				prodPlanta = prodPlantaService.save(prodPlanta);
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}