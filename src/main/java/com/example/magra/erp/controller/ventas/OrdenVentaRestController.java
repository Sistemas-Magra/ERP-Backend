package com.example.magra.erp.controller.ventas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.entity.maestro.ClienteContacto;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajoDetalle;
import com.example.magra.erp.models.entity.ventas.OrdenVenta;
import com.example.magra.erp.models.entity.ventas.OrdenVentaDetalle;
import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.maestros.IClienteContactoService;
import com.example.magra.erp.models.service.maestros.IClienteService;
import com.example.magra.erp.models.service.produccion.IOrdenTrabajoService;
import com.example.magra.erp.models.service.ventas.IOrdenVentaDetalleService;
import com.example.magra.erp.models.service.ventas.IOrdenVentaService;

@RestController
@RequestMapping("/api")
public class OrdenVentaRestController {
	@Autowired
	private IOrdenTrabajoService otService;
	
	@Autowired
	private IOrdenVentaService ordenVentaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IConfiguracionService confService;
	
	@Autowired
	private IClienteContactoService contactoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private IOrdenVentaDetalleService detalleVentaService;
	
	@PutMapping("/orden-venta/upload-files")
	public ResponseEntity<?> subirPlanosEspecificaciones(
				@RequestParam(value="id", required=true) Integer ordenVentaDetalleId,
				@RequestParam(value="number", required=true) Integer i,
				@RequestParam(value="plano", required=true) MultipartFile plano,
				@RequestParam(value="esp", required=true) MultipartFile esp
			) {
		Map<String, Object> response = new HashMap<>();
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		
		try {
			uploadService.copiarConNombre(plano, VariablesGlobales.PLANOS, "plano_" + i + "_" + strDate.replaceAll(":", ""));
			uploadService.copiarConNombre(esp, VariablesGlobales.ESPECIFICACIONES_TECNICAS, "especificaciones_" + i + "_" + strDate.replaceAll(":", ""));
			
			detalleVentaService.updateFilenames("plano_" + i + "_" + strDate.replaceAll(":", ""), "especificaciones_" + i + "_" + strDate.replaceAll(":", ""), ordenVentaDetalleId);
		} catch (IOException e) {
			e.printStackTrace();
			response.put("mensaje", "Error al intentar subir archivos.");			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);		
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/orden-venta/update/{id}")
	public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody OrdenVenta venta){
		Map<String, Object> response = new HashMap<>();
		
		try {
			venta.setEstado(confService.findById(2, "ESTVEN"));
			OrdenVenta ordenVentaSaved = ordenVentaService.save(venta);
			
			OrdenTrabajo ot = new OrdenTrabajo();
			
			ot.setCodigo(LocalDate.now().getYear() + "-" + String.format("%05d", otService.getCatOT()+ 1 ));
			ot.setIdUsuarioCrea(ordenVentaSaved.getIdUsuarioModifica());
			ot.setOrdenVenta(ordenVentaSaved);
			ot.setNombreTrabajo(venta.getNombreTrabajo());
			
			List<OrdenTrabajoDetalle> otdList = new ArrayList<>();
			
			for(OrdenVentaDetalle ovd: ordenVentaSaved.getDetalle()) {
				OrdenTrabajoDetalle otd = new OrdenTrabajoDetalle();
				
				otd.setCantidadPendiente(ovd.getCantidad());
				otd.setCantidadProducida(0);
				otd.setCantidadAceptada(0);
				otd.setCantidadRechazada(0);
				otd.setCantidadDespachada(0);
				otd.setOrdenVentaDetalle(ovd);
				
				otdList.add(otd);
				
			}
			
			ot.setDetalle(otdList);
			ot.setEstado(confService.findById(1, "ESTODT"));
			
			otService.save(ot);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al intentar guardar los datos correspondientes.");			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/orden-venta/get/{id}")
	public OrdenVenta getById(@PathVariable Integer id) {
		return ordenVentaService.getById(id);
	}
	
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