package com.example.magra.erp.models.service.talento_humano;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.talento_humano.IContratoDao;
import com.example.magra.erp.models.entity.talento_humano.Contrato;

@Service
public class ContratoServiceImpl implements IContratoService {
	
	@Autowired
	private IContratoDao contratoDao;

	@Override
	public Contrato save(Contrato contrato) {
		return contratoDao.save(contrato);
	}

	@Override
	public Contrato getContratoByEmpleado(Integer empleadoId) {
		return contratoDao.getContratoByEmpleado(empleadoId);
	}

	@Override
	public Map<String, Object> getDatosContrato(Integer empleadoId, Integer mes, Integer anio) {
		return contratoDao.getDatosContrato(empleadoId, mes, anio);
	}

}
