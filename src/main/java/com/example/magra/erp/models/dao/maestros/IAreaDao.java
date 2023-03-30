package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Area;

public interface IAreaDao extends JpaRepository<Area, Integer> {
	@Query("SELECT a FROM Area a WHERE a.id = ?1 ")
	Area getAreaById(Integer id);
	@Query(value="SELECT a.id FROM mae_area a JOIN mae_subarea s ON s.area_id = a.id WHERE s.id = ?1 ", nativeQuery=true)
	Integer getAreaBySubareaId(Integer subareaId);
}
