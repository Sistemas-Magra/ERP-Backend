package com.example.magra.erp.models.service.despacho;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.helper.DespachoPdfGuiRemision;
import com.example.magra.erp.helper.DespachoPdfGuiaProvisional;
import com.example.magra.erp.models.dao.despacho.IFormularioDao;
import com.example.magra.erp.models.entity.despacho.Formulario;
import com.itextpdf.text.DocumentException;

@Service
public class FormularioServiceImpl implements IFormularioService {
	
	@Autowired
	private IFormularioDao formularioDao;

	@Override
	public Formulario save(Formulario formulario) {
		return formularioDao.save(formulario);
	}

	@Override
	public List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido) {
		return formularioDao.getListadoFormularios(fecha, cliente, nroPedido);
	}

	@Override
	public Formulario getById(Integer id) {
		return formularioDao.getById(id);
	}

	@Override
	public List<Formulario> getFormulariosFromDespacho(Date fecha) {
		return formularioDao.getFormulariosFromDespacho(fecha);
	}

	@Override
	public void actualizarFormularios(Integer id) {
		formularioDao.actualizarFormularios(id);
	}

	@Override
	public void generarProvicional(Formulario formulario) throws FileNotFoundException, DocumentException {
		DespachoPdfGuiaProvisional.generarGuiaProvisional(formulario);
	}

	@Override
	public void generarGuiaRemision(Integer id, Integer usuarioId)
			throws DocumentException, MalformedURLException, IOException {
		Map<String, Object> datos = formularioDao.getDatosFormulario(id, usuarioId);
		List<Map<String, Object>> detalle = formularioDao.getDatosFormularioDetalle(id);
		DespachoPdfGuiRemision.generarGuiaRemision(datos, detalle);
	}

	@Override
	public void updateNroRemision(Integer id) {
		formularioDao.updateNroRemision(id);
	}

	@Override
	public Map<String, Object> getNroRemision(Integer id) {
		return formularioDao.getNroRemision(id);
	}

}