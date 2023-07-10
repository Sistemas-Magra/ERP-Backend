package com.example.magra.erp.models.dao.despacho;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.despacho.Formulario;

public interface IFormularioDao extends JpaRepository<Formulario, Integer> {
	
	@Query(value="SELECT nro_remision FROM desp_formulario WHERE id = ?1", nativeQuery=true)
	Map<String, Object> getNroRemision(Integer id);
	
	@Query(value="EXEC web_d001_man_update_nro_remision ?1", nativeQuery=true)
	void updateNroRemision(Integer id);
	
	@Query(value="EXEC web_d001_sel_obtener_detalle_formulario ?1", nativeQuery=true)
	List<Map<String, Object>> getDatosFormularioDetalle(Integer id);
	
	@Query(value="EXEC web_d001_sel_obtener_datos_formulario ?1 , ?2", nativeQuery=true)
	Map<String, Object> getDatosFormulario(Integer id, Integer usuarioId);
	
	@Query(value="EXEC web_d001_man_update_despachos ?1", nativeQuery=true)
	void actualizarFormularios(Integer id);
	
	@Query("SELECT d.formularios FROM Despacho d WHERE d.fecha = ?1")
	List<Formulario> getFormulariosFromDespacho(Date fecha);
	
	@Query(value="EXEC web_t001_sel_listado_formularios ?1 , ?2 , ?3", nativeQuery=true)
	List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido);
	
	@Query("SELECT f FROM Formulario f WHERE f.id = ?1")
	Formulario getById(Integer id);
}