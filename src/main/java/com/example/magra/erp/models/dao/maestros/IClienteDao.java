package com.example.magra.erp.models.dao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c WHERE UPPER(c.nroDocumentoIdentidad) LIKE UPPER(CONCAT('%', ?1 , '%')) OR UPPER(c.razonSocial) LIKE UPPER(CONCAT('%', ?1 , '%'))")
	List<Cliente> getClientesAutocomplete(String term);
}
