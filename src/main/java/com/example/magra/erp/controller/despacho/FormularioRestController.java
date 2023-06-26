package com.example.magra.erp.controller.despacho;

import java.util.ArrayList;
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

import com.example.magra.erp.models.entity.despacho.Despacho;
import com.example.magra.erp.models.entity.despacho.Formulario;
import com.example.magra.erp.models.service.despacho.IDespachoService;
import com.example.magra.erp.models.service.despacho.IFormularioService;

@RestController
@RequestMapping("/api")
public class FormularioRestController {
	
	@Autowired
	private IFormularioService formularioService;
	
	@Autowired
	private IDespachoService despachoService;
	
	@GetMapping("/formulario/find/{id}")
	public Formulario getById(@PathVariable Integer id) {
		return formularioService.getById(id);
	}
	
	@GetMapping("/formulario/list")
	public List<Map<String, Object>> getListado(
				@RequestParam(value="f", required=false) String fecha,
				@RequestParam(value="c", required=false) String cliente,
				@RequestParam(value="p", required=false) String pedido
			) {
		return formularioService.getListadoFormularios(fecha, cliente, pedido);
	}
	
	@PutMapping("/formulario/update")
	public ResponseEntity<?> update(@RequestBody Formulario formulario, @Valid BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			formularioService.save(formulario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/formulario/create")
	public ResponseEntity<?> create(@RequestBody Formulario formulario, @Valid BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Despacho despacho = despachoService.getDespachoByFecha(formulario.getFecha());
			
			if(despacho == null) {
				despacho = new Despacho();
				
				despacho.setFecha(formulario.getFecha());
			}
			
			if(despacho.getEstado() != null && despacho.getEstado().getTablaAuxiliarDetalleId().getId() == 2) {
				response.put("mensaje", "Ya no se pueden asignar formularios en la fecha indicada.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			
			formulario = formularioService.save(formulario);

			List<Formulario> forms = despacho.getFormularios();
			
			if(forms == null) {
				forms = new ArrayList<>();
			}

			forms.add(formulario);
			despacho.setFormularios(forms);
			
			despacho = despachoService.save(despacho);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}