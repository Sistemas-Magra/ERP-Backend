package com.example.magra.erp.models.service.produccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IOrdenTrabajoDetalleDao;

@Service
public class OrdenTrabajoDetalleImpl implements IOrdenTrabajoDetalleService {
	@Autowired
	private IOrdenTrabajoDetalleDao ordenTrabajoDetaleDao;
}