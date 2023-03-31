package com.example.magra.erp.models.service.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IPeriodoCtsDao;
import com.example.magra.erp.models.entity.maestro.PeriodoCts;

@Service
public class PeriodoCtsServiceImpl implements IPeriodoCtsService {
	
	@Autowired
	private IPeriodoCtsDao ctsDao;

	@Override
	public PeriodoCts getPeriodoCtsActivo() {
		return ctsDao.getPeriodoCtsActivo();
	}

}
