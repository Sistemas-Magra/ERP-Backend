package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IBancoDao;
import com.example.magra.erp.models.entity.maestro.Banco;

@Service
public class BancoServiceImpl implements IBancoService{
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Override
	public List<Banco> getBancosActivos() {
		return bancoDao.getBancosActivos();
	}

}
