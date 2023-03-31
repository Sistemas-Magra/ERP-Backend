package com.example.magra.erp.models.service.talento_humano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.talento_humano.ICeseDao;
import com.example.magra.erp.models.entity.talento_humano.Cese;

@Service
public class CeseServiceImpl implements ICeseService {
	
	@Autowired
	private ICeseDao ceseDao;

	@Override
	public Cese save(Cese cese) {
		return ceseDao.save(cese);
	}

}
