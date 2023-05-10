package com.example.magra.erp.controller.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.seguridad.UsuarioPlanta;
import com.example.magra.erp.models.service.seguridad.IUsuarioPlantaService;

@RestController
@RequestMapping("/api")
public class UsuarioPlantaRestController {
	@Autowired
	private IUsuarioPlantaService usuarioPlantaService;
	
	@GetMapping("/usuario-planta/get-id/{usuarioId}")
	public UsuarioPlanta getUsuarioPlantaByUsuarioId(@PathVariable Integer usuarioId) {
		return usuarioPlantaService.getByUsuarioId(usuarioId);
	}
}
