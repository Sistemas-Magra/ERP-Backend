package com.example.magra.erp.controller.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.seguridad.Modulo;
import com.example.magra.erp.models.service.seguridad.IModuloService;

@RequestMapping("/api")
@RestController
public class ModuloRestController {
	@Autowired
	private IModuloService moduloService;
	
	@GetMapping("/modulo/modulosByUsername/{username}")
	public List<Modulo> getModulosByUsername(@PathVariable String username) {
		return moduloService.findModulosByUsername(username);
	}
}
