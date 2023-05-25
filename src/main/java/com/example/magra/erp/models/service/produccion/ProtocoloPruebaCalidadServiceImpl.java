package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProtocoloPruebaCalidadDao;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;

@Service
public class ProtocoloPruebaCalidadServiceImpl implements IProtocoloPruebaCalidadService {
	
	@Autowired
	private IProtocoloPruebaCalidadDao protocoloDao;

	@Override
	public ProtocoloPruebaCalidad save(ProtocoloPruebaCalidad protocolo) {
		return protocoloDao.save(protocolo);
	}

	@Override
	public List<Map<String, Object>> getListado(String razonSocial, String ordenTrabajo, String producto,
			String fecha) {
		return protocoloDao.getListado(razonSocial, ordenTrabajo, producto, fecha);
	}

	@Override
	public ProtocoloPruebaCalidad getProtocoloById(Integer id) {
		return protocoloDao.getProtocoloById(id);
	}

}