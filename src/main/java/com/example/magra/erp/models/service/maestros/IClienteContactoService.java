package com.example.magra.erp.models.service.maestros;

import com.example.magra.erp.models.entity.maestro.ClienteContacto;

public interface IClienteContactoService {
	ClienteContacto findByCorreo(String correo);
	ClienteContacto save(ClienteContacto contacto);
}