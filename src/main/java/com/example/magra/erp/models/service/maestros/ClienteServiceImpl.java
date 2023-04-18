package com.example.magra.erp.models.service.maestros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.dao.maestros.IClienteDao;
import com.example.magra.erp.models.entity.maestro.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public Map<String, Object> getFromSunat(String nroDocumento, Integer ind) {
		URL obj;
		String param = (ind == 1)?"dni":"ruc";
		Map<String, Object> res = new HashMap<>();
		String url = VariablesGlobales.API_SUNAT + param + "?numero=" + nroDocumento;
		//System.out.println(url);
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Authorization", "Bearer apis-token-4325.653mHngfGKiwmJC1Qt8bEPrQV-hJMXGg");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
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
				if(clienteDao.getClienteByDocumentoIdentidad(nroDocumento) != null) {
					res.put("id", clienteDao.getClienteByDocumentoIdentidad(nroDocumento).getId());
					res.put("contactos", clienteDao.getClienteByDocumentoIdentidad(nroDocumento).getContactos());
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
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			res.put("mensaje", "Error en el formato enviado a SUNAT, verifique los datos e intente nuevamente.");
			return res;
		} catch (ProtocolException e) {
			e.printStackTrace();
			res.put("mensaje", "Error en comunicación con SUNAT. Por favor ingrese los datos manualmente.");
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			res.put("mensaje", "Error en comunicación con SUNAT. Por favor ingrese los datos manualmente.");
			return res;
		}
	}

	@Override
	public List<Cliente> getClientesAutocomplete(String term) {
		return clienteDao.getClientesAutocomplete(term);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

}