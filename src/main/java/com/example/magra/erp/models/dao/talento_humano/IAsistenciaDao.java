package com.example.magra.erp.models.dao.talento_humano;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.talento_humano.AsistenciaPersonal;

public interface IAsistenciaDao extends JpaRepository<AsistenciaPersonal, Integer> {
	@Query(value="EXEC web_m002_man_registrar_asistencia @json = ?1", nativeQuery=true)
	void registrarAsistencias(String json);
}