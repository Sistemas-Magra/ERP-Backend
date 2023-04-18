package com.example.magra.erp.models.service.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IClienteContactoDao;
import com.example.magra.erp.models.entity.maestro.ClienteContacto;

@Service
public class ClienteContactoServiceImpl implements IClienteContactoService {
	@Autowired
	private IClienteContactoDao contactoDao;

	@Override
	public ClienteContacto findByCorreo(String correo) {
		return contactoDao.findByCorreo(correo);
	}

	@Override
	public ClienteContacto save(ClienteContacto contacto) {
		return contactoDao.save(contacto);
	}
}
