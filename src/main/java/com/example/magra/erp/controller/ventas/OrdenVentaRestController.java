package com.example.magra.erp.controller.ventas;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.gestion.Empresa;
import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.entity.maestro.ClienteContacto;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajoDetalle;
import com.example.magra.erp.models.entity.ventas.OrdenVenta;
import com.example.magra.erp.models.entity.ventas.OrdenVentaDetalle;
import com.example.magra.erp.models.entity.ventas.Pago;
import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.gestion.IEmpresaService;
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
	
	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/orden-venta/descargar-archivos/{ind}/{filename:.+}")
	public ResponseEntity<Resource> descargarControlCalidad(@PathVariable String filename, @PathVariable Integer ind) {
		
		Resource recurso = null;
		
		try {
			if(ind == 1) {
				recurso = uploadService.cargar(filename, VariablesGlobales.PLANOS);
			} else {
				recurso = uploadService.cargar(filename, VariablesGlobales.ESPECIFICACIONES_TECNICAS);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/orden-venta/autocomplete/{term}")
	public List<OrdenVenta> autocomplete(@PathVariable String term) {
		return ordenVentaService.autocomplete(term);
	}
	
	@GetMapping("/orden-venta/autocomplete-c/{clienteId}/{term}")
	public List<OrdenVenta> autocompleteByCliente(@PathVariable Integer clienteId, @PathVariable String term) {
		return ordenVentaService.autocompleteByCliente(clienteId, term);
	}
	
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
			String nombrePlano = uploadService.copiarConNombre(plano, VariablesGlobales.PLANOS, "plano_" + i + "_" + strDate.replaceAll(":", ""));
			String nombreEsp = uploadService.copiarConNombre(esp, VariablesGlobales.ESPECIFICACIONES_TECNICAS, "especificaciones_" + i + "_" + strDate.replaceAll(":", ""));
			
			detalleVentaService.updateFilenames(nombrePlano, nombreEsp, ordenVentaDetalleId);
		} catch (IOException e) {
			e.printStackTrace();
			response.put("mensaje", "Error al intentar subir archivos.");			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);		
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/orden-venta/registrar-pago/{id}")
	public ResponseEntity<?> registrarPago(
				@PathVariable Integer id,
				@RequestParam(value="ad", required=true) BigDecimal adelanto,
				@RequestParam(value="pen", required=true) BigDecimal pendiente,
				@RequestParam(value="to", required=true) BigDecimal total,
				@RequestBody Pago pago,
				@Valid BindingResult result
			){
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		OrdenVenta ven = ordenVentaService.getById(id);
		
		List<Pago> pagos = ven.getPagos();
		
		try {
			
			BigDecimal parte1 = total.subtract(pendiente);
			BigDecimal parte2 = adelanto.subtract(pago.getMonto());
			
			if((total.subtract(pendiente).compareTo(adelanto)) == -1 && parte1.compareTo(parte2) == 1) {
				BigDecimal pagoAdelanto = adelanto.subtract(total.subtract(pendiente));
				BigDecimal pagoNoAdelanto = pago.getMonto().subtract(pagoAdelanto);
				
				Pago pago1 = new Pago();
				pago1.setFechaCrea(new Date());
				pago1.setIdUsuarioCrea(pago.getIdUsuarioCrea());
				pago1.setNombreUsuarioCrea(pago.getNombreUsuarioCrea());
				pago1.setIndEsAdelanto(true);
				pago1.setMonto(pagoAdelanto);
				pago1.setTipoPago(pago.getTipoPago());

				
				Pago pago2 = new Pago();
				pago2.setFechaCrea(new Date());
				pago2.setIdUsuarioCrea(pago.getIdUsuarioCrea());
				pago2.setNombreUsuarioCrea(pago.getNombreUsuarioCrea());
				pago2.setIndEsAdelanto(false);
				pago2.setMonto(pagoNoAdelanto);
				pago2.setTipoPago(pago.getTipoPago());
				
				pagos.add(pago1);
				pagos.add(pago2);
			} else {
				pagos.add(pago);
			}
			
			ven.setPagos(pagos);
			ven.setPagoPendiente(pendiente.subtract(pago.getMonto()));
			
			ven = ordenVentaService.save(ven);
			
			if(ven.getPagoPendiente().compareTo(total.subtract(adelanto)) <= 0) {
				
				if(ordenVentaService.getOrdenTrabajoIdByOrdenVenta(ven.getId()) == null) {
					
					OrdenTrabajo ot = new OrdenTrabajo();
					
					ot.setCodigo(LocalDate.now().getYear() + "-" + String.format("%05d", otService.getCatOT()+ 1 ));
					ot.setIdUsuarioCrea(ven.getIdUsuarioModifica());
					ot.setOrdenVenta(ven);
					ot.setNombreTrabajo(ven.getNombreTrabajo());
					
					List<OrdenTrabajoDetalle> otdList = new ArrayList<>();
					
					for(OrdenVentaDetalle ovd: ven.getDetalle()) {
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
					ven.setEstado(confService.findById(3, "ESTVEN"));
					ven = ordenVentaService.save(ven);
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al registrar pago.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		
		response.put("pendiente", ven.getPagoPendiente());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/orden-venta/update/{id}")
	public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody OrdenVenta venta){
		Map<String, Object> response = new HashMap<>();
		
		OrdenVenta ventaNew = null;
		
		try {
			venta.setEstado(confService.findById(2, "ESTVEN"));
			ventaNew = ordenVentaService.save(venta);
			
			if(ventaNew.getTipoPago().getTablaAuxiliarDetalleId().getId() == 1) {
				if(ordenVentaService.getOrdenTrabajoIdByOrdenVenta(ventaNew.getId()) == null) {
					
					OrdenTrabajo ot = new OrdenTrabajo();
					
					ot.setCodigo(LocalDate.now().getYear() + "-" + String.format("%05d", otService.getCatOT()+ 1 ));
					ot.setIdUsuarioCrea(ventaNew.getIdUsuarioModifica());
					ot.setOrdenVenta(ventaNew);
					ot.setNombreTrabajo(ventaNew.getNombreTrabajo());
					
					List<OrdenTrabajoDetalle> otdList = new ArrayList<>();
					
					for(OrdenVentaDetalle ovd: ventaNew.getDetalle()) {
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
					ventaNew.setEstado(confService.findById(3, "ESTVEN"));
					ventaNew = ordenVentaService.save(ventaNew);
					
				}
				
			}
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
	
	
	@PostMapping("/orden-venta/create/{empresaId}")
	public ResponseEntity<?> create(@RequestBody OrdenVenta ordenVenta, @PathVariable Integer empresaId){
		Map<String, Object> response = new HashMap<>();
		
		Empresa empresa = empresaService.getEmpresaById(empresaId);
		
		try {
			//Guarda datos de cliente
			Cliente cliente = clienteService.save(ordenVenta.getCliente());
			ordenVenta.setCliente(cliente);
			
			//Guarda datos de contacto de cliente
			ClienteContacto contacto = contactoService.findByCorreo(ordenVenta.getContacto().getCorreo());
			ordenVenta.setContacto(contacto);
			
			//Guarda orden de venta
			ordenVenta.setCodigo(LocalDate.now().getYear() + "-" + String.format("%05d", ordenVentaService.getCodigo()));
			ordenVenta.setEmpresaPartida(empresa);
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