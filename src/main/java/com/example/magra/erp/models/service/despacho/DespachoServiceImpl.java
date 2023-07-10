package com.example.magra.erp.models.service.despacho;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.despacho.IDespachoDao;
import com.example.magra.erp.models.entity.despacho.Despacho;

@Service
public class DespachoServiceImpl implements IDespachoService {

	@Autowired
	private IDespachoDao despachoDao;

	@Override
	public Despacho getDespachoByFecha(Date fecha) {
		return despachoDao.getDespachoByFecha(fecha);
	}

	@Override
	public Despacho save(Despacho despacho) {
		return despachoDao.save(despacho);
	}

	@Override
	public Despacho getById(Integer id) {
		return despachoDao.getById(id);
	}

}