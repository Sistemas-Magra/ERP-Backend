package com.example.magra.erp.models.dao.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.seguridad.UsuarioPlanta;

public interface IUsuarioPlantaDao extends JpaRepository<UsuarioPlanta, Integer> {
	@Query("SELECT u FROM UsuarioPlanta u WHERE u.idUsuario = ?1")
	UsuarioPlanta getByUsuarioId(Integer usuarioId);
}
