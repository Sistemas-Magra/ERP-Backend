package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.Area;

public interface IAreaService {
	List<Area> findAll();
	Area getById(Integer id);
	Integer getAreaBySubareaId(Integer subareaId);
}
