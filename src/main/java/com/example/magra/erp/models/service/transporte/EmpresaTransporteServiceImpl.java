package com.example.magra.erp.models.service.transporte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.dao.transporte.IEmpresaTransporteDao;
import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;

@Service
public class EmpresaTransporteServiceImpl implements IEmpresaTransporteService {
	@Autowired
	private IEmpresaTransporteDao etDao;

	@Override
	public List<EmpresaTransporte> getAll() {
		return etDao.findAll();
	}

	@Override
	public EmpresaTransporte save(EmpresaTransporte empTrans) {
		return etDao.save(empTrans);
	}

	@Override
	public Map<String, Object> getDatosFromSunat(String nroDocumento) {
		Map<String, Object> res = new HashMap<>();
		
		Integer id = etDao.getIdByNroDocumento(nroDocumento);
		
		if(id != null) {
			res.put("mensaje", "El documento ingresado ya se encuentra registrado.");
			return res;			
		}
		
		URL obj;
		String url = VariablesGlobales.API_SUNAT + "ruc?numero=" + nroDocumento;
		
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Authorization", "Bearer apis-token-4325.653mHngfGKiwmJC1Qt8bEPrQV-hJMXGg");
			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				String responseStr = response.toString();
				
				responseStr = responseStr.replace("{", "").replace("}", "");

				String[] responseArr = responseStr.split(",");
				
				for(String kv: responseArr) {
					String[] kvf = kv.split(":");
					
					res.put(kvf[0].replace("\"", "").replace("\"", ""), kvf[1].replace("\"", "").replace("\"", ""));
				}

				return res;
				
			} else if(responseCode == 404) {
				res.put("mensaje", "El documento que buscó no se encuentra registrado en las entidades estatales.");
				return res;
			} else if(responseCode == 422) {
				res.put("mensaje", "Error en el número de documento ingresado. Verifique que el número de identidad corresponda a un RUC, por favor.");
				return res;
			} else {
				res.put("mensaje", "Error en comunicación con SUNAT. Por favor ingrese los datos manualmente.");
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.put("mensaje", "Error en el formato enviado a SUNAT, verifique los datos e intente nuevamente.");
			return res;
		}
	}
	
}