package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IEmpleadoDao;
import com.example.magra.erp.models.entity.maestro.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoDao empleadoDao;

	@Override
	public List<Empleado> autocomplete(String term) {
		return empleadoDao.autocomplete(term);
	}

}
