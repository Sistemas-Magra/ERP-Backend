package com.example.magra.erp.models.service.despacho;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.despacho.Formulario;
import com.itextpdf.text.DocumentException;

public interface IFormularioService {
	Formulario save(Formulario formulario);
	List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido);
	Formulario getById(Integer id);
	List<Formulario> getFormulariosFromDespacho(Date fecha);
	Map<String, Object> getNroRemision(Integer id);
	void actualizarFormularios(Integer id);
	void updateNroRemision(Integer id);
	void generarProvicional(Formulario formulario) throws FileNotFoundException, DocumentException;
	void generarGuiaRemision(Integer id, Integer usuarioId) throws DocumentException, MalformedURLException, IOException;
}