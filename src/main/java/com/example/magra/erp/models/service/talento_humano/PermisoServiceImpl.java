package com.example.magra.erp.models.service.talento_humano;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.talento_humano.IPermisoDao;
import com.example.magra.erp.models.entity.talento_humano.Permiso;

@Service
public class PermisoServiceImpl implements IPermisoService {
	@Autowired
	private IPermisoDao permisoDao;

	@Override
	public Permiso save(Permiso permiso) {
		return permisoDao.save(permiso);
	}

	@Override
	public List<Map<String, Object>> getPermisosEmpleado(Integer empleadoId) {
		return permisoDao.getPermisosEmpleado(empleadoId);
	}
	
	
}
