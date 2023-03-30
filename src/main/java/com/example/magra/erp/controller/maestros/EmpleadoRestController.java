package com.example.magra.erp.controller.maestros;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.maestro.Area;
import com.example.magra.erp.models.entity.maestro.Empleado;
import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.maestros.IAreaService;
import com.example.magra.erp.models.service.maestros.IEmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@PostMapping("/empleado/registrar-vacaciones")
	public ResponseEntity<?> registrarVacaciones(
				@RequestParam(value="fechaDesde", required=true) String fechaDesde,
				@RequestParam(value="fechaHasta", required=true) String fechaHasta,
				@RequestParam(value="empleadoId", required=true) Integer empleadoId,
				@RequestParam(value="cantidadDias", required=true) Integer cantidadDias,
				@RequestParam(value="userId", required=true) Integer userId
			) {
		Map<String, Object> response = new HashMap<>();
		
		empleadoService.registrarVacaciones(fechaDesde, fechaHasta, empleadoId, cantidadDias, userId);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/empleado/subir-foto/{id}")
	public ResponseEntity<?> subirFoto(@PathVariable Integer id, @RequestParam(value="foto", required=false) MultipartFile foto) {
		Map<String, Object> response = new HashMap<>();
		
		String ruta = VariablesGlobales.EMPLEADO_FOTOS;
		Empleado empleado = empleadoService.getById(id);
		
		if(foto == null) {
			empleado.setFoto(null);
			empleadoService.save(empleado);
		}
		
		if(foto.isEmpty() || foto.getSize() == 0) {
			response.put("mensaje", "Archivo no legible.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		try {
			String nombre = uploadFileService.copiarConNombre(foto, ruta, empleado.getCodigo());
			empleado.setFoto(nombre);
			empleadoService.save(empleado);
		} catch (IOException e) {
			e.printStackTrace();
			response.put("mensaje", "No se pudo cargar la foto al servidor, intente nuevamente.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}

		response.put("mensaje", "Éxito al cargar imagen");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/empleado/update-condicion/{id}/{ind}/{estadoId}")
	public ResponseEntity<?> updateCondicion(@PathVariable Integer id, @PathVariable Integer ind, @PathVariable Integer estadoId) {
		Map<String, Object> response = new HashMap<>();
		
		empleadoService.condicionarPersonal(id, estadoId);
		
		if(ind == 1) {
			empleadoService.condicionarUsuario(id, estadoId);
		}

		response.put("mensaje", "Estado actualizado correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/empleado/update")
	public ResponseEntity<?> update(@RequestBody Empleado empleado) {
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleadoNew = empleadoService.save(empleado);

		response.put("id", empleadoNew.getId());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/empleado/create")
	public ResponseEntity<?> create(@RequestBody Empleado empleado) {
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleadoNew = empleadoService.save(empleado);

		response.put("id", empleadoNew.getId());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/get-foto/{filename:.+}")
	public ResponseEntity<Resource> getFoto(@PathVariable String filename) {
		Resource res = null;
		try {
			res = uploadFileService.cargar(filename, VariablesGlobales.EMPLEADO_FOTOS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(res, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/find/{id}")
	public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Empleado emp = empleadoService.getById(id);
		Area area = areaService.getById(areaService.getAreaBySubareaId(emp.getSubArea().getId()));
		response.put("empleado", emp);
		response.put("area", area);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/autocomplete/{term}")
	public List<Empleado> autocomplete(@PathVariable String term) {
		return empleadoService.autocomplete(term);
	}
	
	@GetMapping("/empleado/total-empleados")
	public Integer cantidadEmpleados() {
		return empleadoService.cantidadEmpleados();
	}
	
	@GetMapping("/empleado/get-codigo")
	public Map<String, Object> getCodigo(
				@RequestParam(value="codigo", required=false, defaultValue="") String codigo
			) {

		Map<String, Object> response = new HashMap<>();
		
		Integer cantidadCeros = codigo.length();
		
		String formato = "%0" + (10 - cantidadCeros) + "d";
		
		String nroCod = String.format(formato, this.empleadoService.cantidadCod(codigo) + 1);
		
		response.put("codigo", codigo+nroCod);
		
		return response;
	}
	
	@GetMapping("/empleado/listado-empleados")
	public List<Map<String, Object>> listadoMaestro(
				@RequestParam(value="nombreCompleto", required=false, defaultValue="") String nombreCompleto,
				@RequestParam(value="nroDoc", required=false, defaultValue="") String nroDoc,
				@RequestParam(value="fecIngDesde", required=false) String fecIngDesde,
				@RequestParam(value="fecIngHasta", required=false) String fecIngHasta,
				@RequestParam(value="indVerInac", required=false, defaultValue="0") Integer indVerInac,
				@RequestParam(value="page", required=false, defaultValue="0") Integer page,
				@RequestParam(value="size", required=false, defaultValue="30") Integer size
			) {
		return empleadoService.listadoMaestro(nombreCompleto, nroDoc, fecIngDesde, fecIngHasta, indVerInac, page, size);
	}
}
