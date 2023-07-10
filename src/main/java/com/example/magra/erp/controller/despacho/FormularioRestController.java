package com.example.magra.erp.controller.despacho;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.despacho.Despacho;
import com.example.magra.erp.models.entity.despacho.Formulario;
import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.despacho.IDespachoService;
import com.example.magra.erp.models.service.despacho.IFormularioService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/api")
public class FormularioRestController {
	
	@Autowired
	private IFormularioService formularioService;
	
	@Autowired
	private IDespachoService despachoService;
	
	@Autowired
	private IConfiguracionService auxiliarService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@PutMapping("/formulario/generar-remision")
	public ResponseEntity<Resource> generarRemision(@RequestBody Formulario formulario) {
		
		Resource recurso = null;
		
		try {
			
			formulario.setEstado(auxiliarService.findById(4, "ESTFRM"));
			
			Formulario newformulario = formularioService.save(formulario);
			
			formularioService.updateNroRemision(newformulario.getId());
			formularioService.generarGuiaRemision(newformulario.getId(), 1);
			
			Map<String, Object> remision = formularioService.getNroRemision(newformulario.getId());
			
			recurso = uploadService.cargar("DESPACHO " + remision.get("nro_remision").toString() + ".pdf", VariablesGlobales.GUIAS_REMISION);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(recurso, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (DocumentException e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(recurso, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename() +"\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		
	}
	
	@PutMapping("/formulario/generar-provisional")
	public ResponseEntity<Resource> generarProvisional(@RequestBody Formulario formulario) {
		
		Resource recurso = null;
		
		try {
			formulario = formularioService.save(formulario);
			formularioService.generarProvicional(formulario);
			recurso = uploadService.cargar("guia_provicional.pdf", VariablesGlobales.GUIAS_PROVICIONAL);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(recurso, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (DocumentException e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(recurso, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename() +"\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		
	}
	
	@PutMapping("/formulario/update-asignacion/{despachoId}")
	public ResponseEntity<?> updateAsignacion(@RequestBody List<Formulario> formularios, @PathVariable Integer despachoId) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			for(Formulario form: formularios) {				
				form = formularioService.save(form);
				formularioService.actualizarFormularios(form.getId());
			}
			
			Despacho despacho = despachoService.getById(despachoId);
			
			despacho.setEstado(auxiliarService.findById(2, "ESTDSP"));
			
			despacho = despachoService.save(despacho);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String, Object>> (response, HttpStatus.OK);
	}
	
	@GetMapping("/formulario/list-despacho")
	public List<Formulario> getFormulariosByDespacho(@RequestParam(value="f", required=true) Date fecha) {
		return formularioService.getFormulariosFromDespacho(fecha);
	}
	
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
				despacho.setEstado(auxiliarService.findById(1, "ESTDSP"));
			}
			
			/*if(despacho.getEstado() != null && despacho.getEstado().getTablaAuxiliarDetalleId().getId() == 2) {
				response.put("mensaje", "Ya no se pueden asignar formularios en la fecha indicada.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}*/
			
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