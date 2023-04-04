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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliarDetalle;
import com.example.magra.erp.models.entity.maestro.Area;
import com.example.magra.erp.models.entity.maestro.Empleado;
import com.example.magra.erp.models.entity.talento_humano.Cese;
import com.example.magra.erp.models.entity.talento_humano.Contrato;
import com.example.magra.erp.models.entity.talento_humano.Permiso;
import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.maestros.IAreaService;
import com.example.magra.erp.models.service.maestros.IEmpleadoService;
import com.example.magra.erp.models.service.maestros.IPeriodoCtsService;
import com.example.magra.erp.models.service.maestros.IPeriodoGratificacionService;
import com.example.magra.erp.models.service.talento_humano.ICeseService;
import com.example.magra.erp.models.service.talento_humano.IContratoService;
import com.example.magra.erp.models.service.talento_humano.IPermisoService;
import com.example.magra.erp.models.service.talento_humano.IVacacionService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private ICeseService ceseService;
	
	@Autowired
	private IPermisoService permisoService;
	
	@Autowired
	private IContratoService contratoService;
	
	@Autowired
	private IConfiguracionService auxiliarService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private IPeriodoCtsService ctsService;
	
	@Autowired
	private IVacacionService vacaService;
	
	@Autowired
	private IPeriodoGratificacionService gratiService;
	
	@PostMapping("/empleado/registrar-contrato")
	public ResponseEntity<?> registrarContrato(@RequestBody Contrato contrato) {
		
		Map<String, Object> response = new HashMap<>();
		
		contratoService.save(contrato);
		empleadoService.save(contrato.getEmpleado());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/get-periodos-cese-activos")
	public Map<String, Object> getPeriodosCeseActivos() {
		Map<String, Object> response = new HashMap<>();
		
		response.put("cts", ctsService.getPeriodoCtsActivo());
		response.put("grati", gratiService.getPeriodoActivo());
		
		return response;
	}
	
	@PostMapping("/empleado/registrar-cese/{idEmpleado}")
	public ResponseEntity<?> registrarCese(@RequestBody Cese cese, @PathVariable Integer idEmpleado) {
		
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleado = empleadoService.getById(idEmpleado);
		
		cese.setEmpleado(empleado);
		
		ceseService.save(cese);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/empleado/registrar-permiso/{idEmpleado}")
	public ResponseEntity<?> registrarPermisos(@RequestBody Permiso permiso, @PathVariable Integer idEmpleado) {
		
		Map<String, Object> response = new HashMap<>();
		
		Integer permisosActivos = vacaService.getVacacionesActivas(idEmpleado);
		
		if(permisosActivos != null && permisosActivos > 0) {
			response.put("mensaje", "El personal ya tiene un permiso activo.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);		
		}
		
		Empleado empleado = empleadoService.getById(idEmpleado);
		TablaAuxiliarDetalle estado = auxiliarService.findTablaAuxiliarDetalleById(1, "ESTPER");
		
		permiso.setEmpleado(empleado);
		permiso.setEstado(estado);
		
		permisoService.save(permiso);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/empleado/registrar-vacaciones")
	public ResponseEntity<?> registrarVacaciones(
				@RequestParam(value="fechaDesde", required=true) String fechaDesde,
				@RequestParam(value="fechaHasta", required=true) String fechaHasta,
				@RequestParam(value="empleadoId", required=true) Integer empleadoId,
				@RequestParam(value="cantidadDias", required=true) Integer cantidadDias,
				@RequestParam(value="userId", required=true) Integer userId
			) {
		Map<String, Object> response = new HashMap<>();
		
		Integer vacacionesActivas = vacaService.getVacacionesActivas(empleadoId);
		
		if(vacacionesActivas != null && vacacionesActivas > 0) {
			response.put("mensaje", "El personal ya tiene vacaciones activas.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);		
		}
		
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
		
		Integer conteoEmpleado =  empleadoService.getByIdentidad(empleado.getTipoDocumentoIdentidad().getTablaAuxiliarDetalleId().getId(), empleado.getNroDocumentoIdentidad());
		
		if(conteoEmpleado != null && conteoEmpleado > 0) {
			response.put("mensaje", "Ya existe un empleado con el número de identidad y el tipo de documento de identidad ingresados.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);			
		}
		
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
	
	@GetMapping("/empleado/get-activos")
	public List<Map<String, Object>> getListadoActivos(
			@RequestParam(value="fecha", required=true) String fecha,
			@RequestParam(value="indVerActiv", required=true) Integer indVerActiv) {
		return empleadoService.getListActivos(fecha, indVerActiv);
	}
}
