package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.ClienteContacto;

public interface IClienteContactoDao extends JpaRepository<ClienteContacto, Integer> {
	@Query("SELECT cc FROM ClienteContacto cc WHERE cc.correo = ?1")
	ClienteContacto findByCorreo(String correo);
}
