package com.example.magra.erp.models.service.produccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProduccionRegistroMezclaDao;
import com.example.magra.erp.models.entity.produccion.ProduccionRegistroMezcla;

@Service
public class FormatosProduccionServiceImpl implements IFormatosProduccionService{
	
	@Autowired
	private IProduccionRegistroMezclaDao mezclaDao;

	@Override
	public ProduccionRegistroMezcla saveMezcla(ProduccionRegistroMezcla prodMezcla) {
		return mezclaDao.save(prodMezcla);
	}

}