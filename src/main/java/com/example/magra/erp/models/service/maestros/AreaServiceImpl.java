package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IAreaDao;
import com.example.magra.erp.models.entity.maestro.Area;

@Service
public class AreaServiceImpl implements IAreaService {
	@Autowired
	private IAreaDao areaDao;

	@Override
	public List<Area> findAll() {
		return areaDao.findAll();
	}

	@Override
	public Area getById(Integer id) {
		return areaDao.getAreaById(id);
	}

	@Override
	public Integer getAreaBySubareaId(Integer subareaId) {
		return areaDao.getAreaBySubareaId(subareaId);
	}
}
