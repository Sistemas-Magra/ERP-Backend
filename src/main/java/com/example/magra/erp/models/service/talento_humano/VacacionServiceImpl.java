package com.example.magra.erp.models.service.talento_humano;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.talento_humano.IVacacionDao;

@Service
public class VacacionServiceImpl implements IVacacionService {
	
	@Autowired
	private IVacacionDao vacacionDao;

	@Override
	public List<Map<String, Object>> getVacacionesEmpleado(Integer empleadoId) {
		return vacacionDao.getVacacionesEmpleado(empleadoId);
	}

}
