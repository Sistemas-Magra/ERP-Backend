package com.example.magra.erp.controller.talento_humano;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.talento_humano.IPermisoService;

@RestController
@RequestMapping("/api")
public class PermisoRestController {
	@Autowired
	private IPermisoService permisoService;
	
	@GetMapping("/permiso/listado-permisos-empleado/{empleadoId}")
	public List<Map<String, Object>> getListadoPorEmpleado(@PathVariable Integer empleadoId) {
		return permisoService.getPermisosEmpleado(empleadoId);
	}
}
