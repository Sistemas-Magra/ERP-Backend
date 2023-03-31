package com.example.magra.erp.models.dao.talento_humano;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.talento_humano.Permiso;

public interface IPermisoDao extends JpaRepository<Permiso, Integer> {
	
	@Query(value="SELECT FORMAT(vac.fecha_inicio, 'dd MMM yyyy') as fecha_inicio, "
			+ "			 FORMAT(vac.fecha_fin, 'dd MMM yyyy') as fecha_fin, "
			+ "			 DATEDIFF(day, vac.fecha_fin, vac.fecha_inicio) + 1 as dias,"
			+ "			 vac.estado_id, "
			+ " 		 est.nombre as estado,"
			+ " 		 usu.username "
			+ " FROM emp_permiso vac "
			+ " JOIN cfg_tabla_auxiliar_detalle est ON est.id = vac.estado_id AND est.cod_tabla_auxiliar = vac.estado_cod_tabla_auxiliar "
			+ " JOIN sti_usuario usu ON usu.id = vac.id_usuario_crea "
			+ " WHERE vac.empleado_id = ?1", nativeQuery=true)
	List<Map<String, Object>> getPermisosEmpleado(Integer empleadoId);

}
