package com.example.magra.erp.models.service.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.ITipoCambioDao;
import com.example.magra.erp.models.entity.maestro.TipoCambio;

@Service
public class TipoCambioServiceImpl implements ITipoCambioService{
	
	@Autowired
	private ITipoCambioDao tipoCambioDao;

	@Override
	public TipoCambio getUltimoTipoCambio() {
		return tipoCambioDao.getUltimoTipoCambio();
	}

}
