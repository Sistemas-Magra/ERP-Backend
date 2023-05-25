package com.example.magra.erp.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCargaTrabajo;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCargaTrabajoMuestra;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaRotura;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaRoturaMuestra;

public class ProduccionExcelProtocoloPrueba {
	public static void generarProtocolo(List<ProtocoloPruebaCalidad> protocolos) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		
		//Estilos
		CellStyle base = workbook.createCellStyle();
		base.setAlignment(HorizontalAlignment.CENTER);
		Font fBase = workbook.createFont();
		fBase.setFontHeightInPoints((short) 10);
		base.setFont(fBase);
		
		CellStyle centradoNegritaTituloDocumento = workbook.createCellStyle();
		centradoNegritaTituloDocumento.setAlignment(HorizontalAlignment.CENTER);
		Font fontCNTD = workbook.createFont();
		fontCNTD.setBold(true);
		fontCNTD.setFontHeightInPoints((short) 16);
		centradoNegritaTituloDocumento.setFont(fontCNTD);
		
		CellStyle centradoNegrita = workbook.createCellStyle();
		centradoNegrita.setAlignment(HorizontalAlignment.CENTER);
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 9);
		centradoNegrita.setFont(font);
		
		CellStyle centradoNegritaTitulo = workbook.createCellStyle();
		centradoNegritaTitulo.setAlignment(HorizontalAlignment.CENTER);
		Font fontN = workbook.createFont();
		fontN.setBold(true);
		fontN.setFontHeightInPoints((short) 12);
		centradoNegritaTitulo.setFont(fontN);
		centradoNegritaTitulo.setWrapText(true);
		
		CellStyle centradoTitulo = workbook.createCellStyle();
		centradoTitulo.setAlignment(HorizontalAlignment.CENTER);
		Font fontT = workbook.createFont();
		fontT.setFontHeightInPoints((short) 12);
		centradoTitulo.setFont(fontT);
		centradoTitulo.setWrapText(true);
		
		CellStyle centradoNegritaSubrayado = workbook.createCellStyle();
		centradoNegritaSubrayado.setAlignment(HorizontalAlignment.CENTER);
		centradoNegritaSubrayado.setBorderBottom(BorderStyle.THIN);;
		Font fCNS = workbook.createFont();
		fCNS.setBold(true);
		fCNS.setFontHeightInPoints((short) 8.5);
		centradoNegritaSubrayado.setFont(fCNS);
		
		CellStyle negrita = workbook.createCellStyle();
		Font fn = workbook.createFont();
		fn.setBold(true);
		fn.setFontHeightInPoints((short) 10);
		negrita.setFont(fn);
		
		CellStyle numeros = workbook.createCellStyle();
		numeros.setAlignment(HorizontalAlignment.LEFT);
		numeros.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		numeros.setFont(fn);
		
		CellStyle fechas = workbook.createCellStyle();
		fechas.setAlignment(HorizontalAlignment.LEFT);
		fechas.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));
		fechas.setFont(fn);
		
		CellStyle separador = workbook.createCellStyle();
		separador.setBorderBottom(BorderStyle.THICK);
		
		CellStyle sObra = workbook.createCellStyle();
		sObra.setWrapText(true);
		sObra.setVerticalAlignment(VerticalAlignment.TOP);
		
		for(ProtocoloPruebaCalidad protocolo: protocolos) {
			Sheet sheet = workbook.createSheet(protocolo.getProducto().getNombre().replaceAll("/", "|"));
			
			sheet.setColumnWidth(0, 960);
			sheet.setColumnWidth(1, 1600);
			sheet.setColumnWidth(2, 1600);
			sheet.setColumnWidth(3, 1600);
			sheet.setColumnWidth(4, 1600);
			sheet.setColumnWidth(5, 1600);

			sheet.setColumnWidth(9, 1600);
			sheet.setColumnWidth(10, 1600);
			sheet.setColumnWidth(11, 1600);
			sheet.setColumnWidth(12, 1600);
			sheet.setColumnWidth(13, 1600);
			sheet.setColumnWidth(14, 1600);
			sheet.setColumnWidth(15, 1600);
			sheet.setColumnWidth(16, 1600);
			sheet.setColumnWidth(17, 1600);
			
	        int pictureType = Workbook.PICTURE_TYPE_PNG;
	        
	        FileInputStream fis = new FileInputStream("C:/Proyecto/imgs/logo_carta_garantia.png");
	        byte[] imageBytes =  IOUtils.toByteArray(fis);
	        
	        int pictureIdx = workbook.addPicture(imageBytes, pictureType);
	        CreationHelper helper = workbook.getCreationHelper();
	        Drawing<?> drawing = sheet.createDrawingPatriarch();
	        ClientAnchor anchor = helper.createClientAnchor();
	        anchor.setCol1(0);
	        anchor.setRow1(0);
	        anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
	        Picture picture = drawing.createPicture(anchor, pictureIdx);
	        picture.resize();

			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			
			CellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	        cell.setCellStyle(style);
			
			Integer cColumnCT = protocolo.getPruebasCargaTrabajo().get(0).getMuestras().size();
			Integer cColumnCR = protocolo.getPruebasRotura().get(0).getMuestras().size();
			
			Integer totalColumnas = cColumnCT + cColumnCR + 2;
	        
	        CellRangeAddress mrTitulo  = new CellRangeAddress(5, 5, 0, totalColumnas);
			Row rtitulo = sheet.createRow(5);			
			Cell ctitulo = rtitulo.createCell(0);
			ctitulo.setCellValue("PROTOCOLO DE PRUEBA POSTES");
			ctitulo.setCellStyle(centradoNegritaTituloDocumento);
			sheet.addMergedRegion(mrTitulo);
			
	        CellRangeAddress mrNorma  = new CellRangeAddress(6, 6, 0, 4);
			Row rDatosEncabezado = sheet.createRow(6);
			Cell cNorma = rDatosEncabezado.createCell(0);
			cNorma.setCellValue("NTP 339.027 - 2002");
			cNorma.setCellStyle(negrita);
			sheet.addMergedRegion(mrNorma);
			
			Cell cFabricante = rDatosEncabezado.createCell(5);
			cFabricante.setCellValue("FABRICANTE");
			cFabricante.setCellStyle(negrita);

	        CellRangeAddress mrFV  = new CellRangeAddress(6, 6, 7, 9);
			Cell cFabricanteValue = rDatosEncabezado.createCell(7);
			cFabricanteValue.setCellValue("COMPAÑÍA MAGRA S.A.C.");
			cFabricanteValue.setCellStyle(centradoNegritaSubrayado);

			sheet.addMergedRegion(mrFV);
			String[] auxListSettings = {"bBottom", "sNegrita", "hCenter"};
			setCellBorderStylesSetting(workbook, sheet, mrFV, BorderStyle.THIN, auxListSettings);
			
			Cell cPoste = rDatosEncabezado.createCell(11);
			cPoste.setCellValue("POSTES C.A.C. DE ");
			cPoste.setCellStyle(negrita);

	        CellRangeAddress mrPV  = new CellRangeAddress(6, 6, 14, totalColumnas);
			Cell cPosteValor = rDatosEncabezado.createCell(14);
			cPosteValor.setCellValue(protocolo.getProducto().getNombre());
			cPosteValor.setCellStyle(centradoNegritaSubrayado);
			
			Cell cPosteValorAux1 = rDatosEncabezado.createCell(15);
			cPosteValorAux1.setCellStyle(centradoNegritaSubrayado);
			
			Cell cPosteValorAux2 = rDatosEncabezado.createCell(16);
			cPosteValorAux2.setCellStyle(centradoNegritaSubrayado);
			
			Cell cPosteValorAux3 = rDatosEncabezado.createCell(17);
			cPosteValorAux3.setCellStyle(centradoNegritaSubrayado);
			
			Cell cPosteValorAux4 = rDatosEncabezado.createCell(18);
			cPosteValorAux4.setCellStyle(centradoNegritaSubrayado);

			sheet.addMergedRegion(mrPV);
			
			//Línea separadora
			Row rlinea1 = sheet.createRow(7);
			for(int i = 0; i <=totalColumnas; i++) {
				Cell c = rlinea1.createCell(i);
				c.setCellStyle(separador);
			}
			
			Row rdatosProducto1 = sheet.createRow(8);
			
			Cell entLicitante = rdatosProducto1.createCell(0);
			entLicitante.setCellValue("ENTIDAD LICITANTE:");
			entLicitante.setCellStyle(negrita);
			
			Cell entLicitanteValor = rdatosProducto1.createCell(5);
			entLicitanteValor.setCellValue(protocolo.getEntidadLicitante());
			entLicitanteValor.setCellStyle(negrita);
			
			Cell longitud = rdatosProducto1.createCell(9);
			longitud.setCellValue("LONGITUD (m)");
			longitud.setCellStyle(negrita);
			
			Cell longitudValor = rdatosProducto1.createCell(14);
			longitudValor.setCellValue(protocolo.getProducto().getLongitud().toString());
			longitudValor.setCellStyle(numeros);
			
			Row rdatosProducto2 = sheet.createRow(9);
			
			Cell contratista = rdatosProducto2.createCell(0);
			contratista.setCellValue("CONTRATISTA:");
			contratista.setCellStyle(negrita);
			
			Cell contratistaValor = rdatosProducto2.createCell(5);
			contratistaValor.setCellValue(protocolo.getOrdenVenta().getCliente().getRazonSocial());
			contratistaValor.setCellStyle(negrita);
			
			Cell empotramiento = rdatosProducto2.createCell(9);
			empotramiento.setCellValue("EMPOTRAMIENTO(m):");
			empotramiento.setCellStyle(negrita);
			
			Cell empotramientoValor = rdatosProducto2.createCell(14);
			empotramientoValor.setCellValue(protocolo.getEmpotramiento().toString());
			empotramientoValor.setCellStyle(numeros);
			
			Row rdatosProducto3 = sheet.createRow(10);
			
			Cell oCompra = rdatosProducto3.createCell(0);
			oCompra.setCellValue("O/COMPRA:");
			oCompra.setCellStyle(negrita);
			
			Cell coCompraValor = rdatosProducto3.createCell(5);
			coCompraValor.setCellValue(protocolo.getOrdenVenta().getCodigo());
			coCompraValor.setCellStyle(negrita);
			
			Cell lUtil = rdatosProducto3.createCell(9);
			lUtil.setCellValue("LONGITUD ÚTIL (m):");
			lUtil.setCellStyle(negrita);
			
			Cell lUtilValor = rdatosProducto3.createCell(14);
			lUtilValor.setCellValue(protocolo.getProducto().getLongitud().subtract(protocolo.getEmpotramiento()).toString());
			lUtilValor.setCellStyle(numeros);
			
			Row rdatosProducto4 = sheet.createRow(11);
			
			Cell lote = rdatosProducto4.createCell(0);
			lote.setCellValue("LOTE:");
			lote.setCellStyle(negrita);
			
			Cell loteValor = rdatosProducto4.createCell(5);
			loteValor.setCellValue(protocolo.getLote() + " POSTES");
			loteValor.setCellStyle(negrita);
			
			Cell cTrabajo = rdatosProducto4.createCell(9);
			cTrabajo.setCellValue("CARGA DE TRABAJO (Kg):");
			cTrabajo.setCellStyle(negrita);
			
			Cell cTrabajoValor = rdatosProducto4.createCell(14);
			cTrabajoValor.setCellValue(protocolo.getProducto().getCargaTrabajo().toString());
			cTrabajoValor.setCellStyle(numeros);
			
			Row rdatosProducto5 = sheet.createRow(12);

			Cell fechaPrueba = rdatosProducto5.createCell(0);
			fechaPrueba.setCellValue("FECHA DE PRUEBA:");
			fechaPrueba.setCellStyle(negrita);

	        CellRangeAddress mrFecha  = new CellRangeAddress(12, 12, 5, 7);
			Cell fechaPruebaValor = rdatosProducto5.createCell(5);
			fechaPruebaValor.setCellValue(protocolo.getFechaInicio());
			fechaPruebaValor.setCellStyle(fechas);

			sheet.addMergedRegion(mrFecha);
			
			Cell cSeguridad = rdatosProducto5.createCell(9);
			cSeguridad.setCellValue("COEFICIENTE DE SEGURIDAD:");
			cSeguridad.setCellStyle(negrita);
			
			Cell cSeguridadValor = rdatosProducto5.createCell(14);
			cSeguridadValor.setCellValue(protocolo.getCoeficienteSeguridad().toString());
			cSeguridadValor.setCellStyle(numeros);
			
			Row rdatosProducto6 = sheet.createRow(13);
			
			Cell obra = rdatosProducto6.createCell(0);
			obra.setCellValue("OBRA:");
			obra.setCellStyle(negrita);
			
			Cell cRotura = rdatosProducto6.createCell(9);
			cRotura.setCellValue("CARGA DE ROTURA (kg.):");
			cRotura.setCellStyle(negrita);
			
			Cell cRoturaValor = rdatosProducto6.createCell(14);
			cRoturaValor.setCellValue(protocolo.getCoeficienteSeguridad().multiply(protocolo.getProducto().getCargaTrabajo()).toString());
			cRoturaValor.setCellStyle(numeros);
			
			Row rdatosProducto7 = sheet.createRow(14);

	        CellRangeAddress mrObra  = new CellRangeAddress(14, 20, 0, 7);
			Cell obraValor = rdatosProducto7.createCell(0);
			obraValor.setCellValue("\"" + protocolo.getOrdenVenta().getNombreTrabajo() + "\"");
			obraValor.setCellStyle(sObra);

			sheet.addMergedRegion(mrObra);
			
			Row rdatosProducto8 = sheet.createRow(15);
			
			Cell deflexMax = rdatosProducto8.createCell(9);
			deflexMax.setCellValue("DEFLEX. MAX.<:");
			deflexMax.setCellStyle(negrita);
			
			Cell deflexMaxValor = rdatosProducto8.createCell(14);
			deflexMaxValor.setCellValue(protocolo.getDeflexMax() + " mm");
			deflexMaxValor.setCellStyle(negrita);
			
			Row rdatosProducto9 = sheet.createRow(16);
			
			Cell deformMax = rdatosProducto9.createCell(9);
			deformMax.setCellValue("DEFORM. MAX. PERMANENTE <:");
			deformMax.setCellStyle(negrita);
			
			Cell deformMaxValor = rdatosProducto9.createCell(14);
			deformMaxValor.setCellValue(protocolo.getDeformMax() + " mm");
			deformMaxValor.setCellStyle(negrita);
			
			Row rdatosProducto10 = sheet.createRow(18);
			
			Cell tPrueba = rdatosProducto10.createCell(9);
			tPrueba.setCellValue("TIPO DE PRUEBA:");
			tPrueba.setCellStyle(negrita);
			
			Cell tPruebaValor = rdatosProducto10.createCell(14);
			tPruebaValor.setCellValue(protocolo.getTipoPrueba().getNombre());
			tPruebaValor.setCellStyle(negrita);
			
			//Línea separadora
			Row rlinea2 = sheet.createRow(20);
			for(int i = 0; i <=totalColumnas; i++) {
				Cell c = rlinea2.createCell(i);
				c.setCellStyle(separador);
			}
			
			Row rdatosProducto11 = sheet.createRow(22);
			Row rdatosProducto12 = sheet.createRow(23);
			Row rLabelPorcentaje = sheet.createRow(24);
			Row rLabelKg = sheet.createRow(25);
			Row rLabelFlechas = sheet.createRow(26);
			Row rLabelD = sheet.createRow(27);
			
			//Muestras Carga Trabajo
	        CellRangeAddress mrTitPruCarTrab  = new CellRangeAddress(22, 22, 0, cColumnCT);
			
			Cell tTitPruCarTrab = rdatosProducto11.createCell(0);
			tTitPruCarTrab.setCellValue("PRUEBA DE CARGA DE TRABAJO");
			tTitPruCarTrab.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrTitPruCarTrab);
			setCellBorderStyles(workbook, sheet, mrTitPruCarTrab, BorderStyle.THIN);
			
	        CellRangeAddress labelMuestraCT  = new CellRangeAddress(23, 27, 0, 0);
			
			Cell labelMuestraV = rdatosProducto12.createCell(0);
			labelMuestraV.setCellValue("MUESTRA");

			sheet.addMergedRegion(labelMuestraCT);
			String[] labelMuestraSettings = {"bAll", "negrita", "rVertical", "vCenter"};
			setCellBorderStylesSetting(workbook, sheet, labelMuestraCT, BorderStyle.THIN, labelMuestraSettings);
			
	        CellRangeAddress rmLabelPorcCT  = new CellRangeAddress(23, 23, 1, cColumnCT);
			
			Cell cLabelPorcCT = rdatosProducto12.createCell(1);
			cLabelPorcCT.setCellValue("PORCENTAJES DE CARGA NOMINAL DE ROTURA");
			cLabelPorcCT.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(rmLabelPorcCT);
			setCellBorderStyles(workbook, sheet, rmLabelPorcCT, BorderStyle.THIN);
			
			List<ProtocoloPruebaCargaTrabajoMuestra> cTrabMuestra = protocolo.getPruebasCargaTrabajo().get(0).getMuestras();
			String[] settings = {"hCenter", "bAll"};
			
			for (ProtocoloPruebaCargaTrabajoMuestra muestra: cTrabMuestra) {
				Cell cMuestraPorc = rLabelPorcentaje.createCell(cTrabMuestra.indexOf(muestra) + 1);
				Cell cMuestraKg = rLabelKg.createCell(cTrabMuestra.indexOf(muestra) + 1);
				Cell cMuestraD = rLabelD.createCell(cTrabMuestra.indexOf(muestra) + 1);
				
				cMuestraPorc.setCellValue(muestra.getPorcentaje().getAbreviatura());
				setCellBorders(workbook, cMuestraPorc, BorderStyle.THIN, settings, null, null);
				
				cMuestraKg.setCellValue(muestra.getKilogramosPrueba()!=null?muestra.getKilogramosPrueba().toString().replace(".00", "") + "Kg.":"");
				setCellBorders(workbook, cMuestraKg, BorderStyle.THIN, settings, (short) 8, null);
				
				cMuestraD.setCellValue(muestra.getKilogramosPrueba()!=null?"D":"");
				setCellBorders(workbook, cMuestraD, BorderStyle.THIN, settings, null, null);
			}
			
	        CellRangeAddress mrTitFlechaCT  = new CellRangeAddress(26, 26, 1, cColumnCT);
			
			Cell cTitFlechaCT = rLabelFlechas.createCell(1);
			cTitFlechaCT.setCellValue("FLECHAS EN MM.");
			cTitFlechaCT.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrTitFlechaCT);
			setCellBorderStyles(workbook, sheet, mrTitFlechaCT, BorderStyle.THIN);
			
			List<ProtocoloPruebaCargaTrabajo> cargas = protocolo.getPruebasCargaTrabajo();
			
			for(ProtocoloPruebaCargaTrabajo carga: cargas) {
				Row rowAux = sheet.createRow(28 + cargas.indexOf(carga));
				
				List<ProtocoloPruebaCargaTrabajoMuestra> muestras = carga.getMuestras();

				Cell cellAuxIndex = rowAux.createCell(0);
				cellAuxIndex.setCellValue(cargas.indexOf(carga) + 1);
				setCellBorders(workbook, cellAuxIndex, BorderStyle.THIN, settings, null, "index");
				
				for(ProtocoloPruebaCargaTrabajoMuestra m: muestras) {

					Cell cellAux = rowAux.createCell(muestras.indexOf(m) + 1);
					cellAux.setCellValue(m.getValor().toString());
					setCellBorders(workbook, cellAux, BorderStyle.THIN, settings, null, null);
				}
			}
			
			//Muestras Carga Rotura
	        CellRangeAddress mrTitPruCarRot  = new CellRangeAddress(22, 22, cColumnCT + 3, cColumnCR + cColumnCT + 2);
			
			Cell tTitPruCarRot = rdatosProducto11.createCell(cColumnCT + 3);
			tTitPruCarRot.setCellValue("PRUEBA DE CARGA DE ROTURA");
			tTitPruCarRot.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrTitPruCarRot);
			setCellBorderStyles(workbook, sheet, mrTitPruCarRot, BorderStyle.THIN);
			
	        CellRangeAddress rmLabelPorcCR  = new CellRangeAddress(23, 23, cColumnCT + 3, cColumnCR + cColumnCT + 2);
			
			Cell cLabelPorcCR = rdatosProducto12.createCell(cColumnCT + 3);
			cLabelPorcCR.setCellValue("PORCENTAJES DE CARGA NOMINAL DE ROTURA");
			cLabelPorcCR.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(rmLabelPorcCR);
			setCellBorderStyles(workbook, sheet, rmLabelPorcCR, BorderStyle.THIN);
			
			List<ProtocoloPruebaRoturaMuestra> cRotMuestra = protocolo.getPruebasRotura().get(0).getMuestras();
			String[] settingsCR = {"hCenter", "bAll"};
			
			for (ProtocoloPruebaRoturaMuestra muestra: cRotMuestra) {
				Cell cMuestraPorc = rLabelPorcentaje.createCell(cRotMuestra.indexOf(muestra) + cColumnCT + 3);
				Cell cMuestraKg = rLabelKg.createCell(cRotMuestra.indexOf(muestra) + cColumnCT + 3);
				Cell cMuestraD = rLabelD.createCell(cRotMuestra.indexOf(muestra) + cColumnCT + 3);
				
				cMuestraPorc.setCellValue(muestra.getPorcentaje().getAbreviatura());
				setCellBorders(workbook, cMuestraPorc, BorderStyle.THIN, settingsCR, null, null);
				
				cMuestraKg.setCellValue(muestra.getKilogramosPrueba()!=null?muestra.getKilogramosPrueba().toString().replace(".00", "") + "Kg.":"");
				setCellBorders(workbook, cMuestraKg, BorderStyle.THIN, settingsCR, (short) 8, null);
				
				cMuestraD.setCellValue(muestra.getKilogramosPrueba()!=null?"D":"");
				setCellBorders(workbook, cMuestraD, BorderStyle.THIN, settingsCR, null, null);
			}
			
	        CellRangeAddress mrTitFlechaCR  = new CellRangeAddress(26, 26, cColumnCT + 3, cColumnCR + cColumnCT + 2);
			
			Cell cTitFlechaCR = rLabelFlechas.createCell(cColumnCT + 3);
			cTitFlechaCR.setCellValue("FLECHAS EN MM.");
			cTitFlechaCR.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrTitFlechaCR);
			setCellBorderStyles(workbook, sheet, mrTitFlechaCR, BorderStyle.THIN);
			
			List<ProtocoloPruebaRotura> cargasCR = protocolo.getPruebasRotura();
			
			for(ProtocoloPruebaRotura carga: cargasCR) {
				Row rowAux = sheet.getRow(28 + cargasCR.indexOf(carga));
				
				List<ProtocoloPruebaRoturaMuestra> muestras = carga.getMuestras();
				
				for(ProtocoloPruebaRoturaMuestra m: muestras) {

					Cell cellAux = rowAux.createCell(muestras.indexOf(m) + cColumnCT + 3);
					cellAux.setCellValue(m.getValor()!=null?m.getValor().toString():"");
					setCellBorders(workbook, cellAux, BorderStyle.THIN, settings, null, null);
				}
			}
			
			//Línea separadora
			Integer rows = Integer.max(cargas.size(), cargasCR.size());
			
			Row rlinea3 = sheet.createRow(28 + rows);
			for(int i = 0; i <=totalColumnas; i++) {
				Cell c = rlinea3.createCell(i);
				c.setCellStyle(separador);
			}
			
			Row tConclusion = sheet.createRow(30 + rows);
			Cell cConclusion = tConclusion.createCell(0);
			cConclusion.setCellValue("CONCLUSIÓN:");


			Row tConclusionValor = sheet.createRow(31 + rows);
	        CellRangeAddress mrConclusion  = new CellRangeAddress(31 + rows, 40 + rows, 0, cColumnCR + cColumnCT + 2);
			
			Cell cConclusionValor = tConclusionValor.createCell(0);
			cConclusionValor.setCellValue(protocolo.getConclusion());
			cConclusionValor.setCellStyle(sObra);

			sheet.addMergedRegion(mrConclusion);
			
			Row rlinea4 = sheet.createRow(41 + rows);
			for(int i = 0; i <=totalColumnas; i++) {
				Cell c = rlinea4.createCell(i);
				c.setCellStyle(separador);
			}
			
			Integer rTres = totalColumnas%3;
			Integer ancho = (totalColumnas - rTres)/3;

	        Row rNomFirma = sheet.createRow(43 + rows);
	        CellRangeAddress mrNomFirma1  = new CellRangeAddress(43 + rows, 43 + rows, 0, ancho);
			
			Cell cNomFirma1 = rNomFirma.createCell(0);
			cNomFirma1.setCellValue("");
			cNomFirma1.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrNomFirma1);

	        CellRangeAddress mrNomFirma2  = new CellRangeAddress(43 + rows, 43 + rows, totalColumnas - 2*ancho + 1, totalColumnas - ancho);
			
			Cell cNomFirma2 = rNomFirma.createCell(totalColumnas - 2*ancho + 1);
			cNomFirma2.setCellValue(protocolo.getOrdenVenta().getCliente().getRazonSocial());
			cNomFirma2.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrNomFirma2);

	        CellRangeAddress mrNomFirma3  = new CellRangeAddress(43 + rows, 43 + rows, totalColumnas - ancho + 1, totalColumnas);
			
			Cell cNomFirma3 = rNomFirma.createCell(totalColumnas - ancho + 1);
			cNomFirma3.setCellValue("MAGRA S.A.C.");
			cNomFirma3.setCellStyle(centradoNegritaTitulo);

			sheet.addMergedRegion(mrNomFirma3);

	        Row rLineaFirma = sheet.createRow(48 + rows);
	        CellRangeAddress mrLineaFirma1  = new CellRangeAddress(48 + rows, 48 + rows, 0, ancho);
			
			Cell cLineaFirma1 = rLineaFirma.createCell(0);
			cLineaFirma1.setCellValue("_____________________________");
			cLineaFirma1.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrLineaFirma1);

	        CellRangeAddress mrLineaFirma2  = new CellRangeAddress(48 + rows, 48 + rows, totalColumnas - 2*ancho + 1, totalColumnas - ancho);
			
			Cell cLineaFirma2 = rLineaFirma.createCell(totalColumnas - 2*ancho + 1);
			cLineaFirma2.setCellValue("_____________________________");
			cLineaFirma2.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrLineaFirma2);

	        CellRangeAddress mrLineaFirma3  = new CellRangeAddress(48 + rows, 48 + rows, totalColumnas - ancho + 1, totalColumnas);
			
			Cell cLineaFirma3 = rLineaFirma.createCell(totalColumnas - ancho + 1);
			cLineaFirma3.setCellValue("_____________________________");
			cLineaFirma3.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrLineaFirma3);

	        Row rPerFirma = sheet.createRow(49 + rows);
	        CellRangeAddress mrPerFirma1  = new CellRangeAddress(49 + rows, 49 + rows, 0, ancho);
			
			Cell cPerFirma1 = rPerFirma.createCell(0);
			cPerFirma1.setCellValue("");
			cPerFirma1.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrPerFirma1);

	        CellRangeAddress mrPerFirma2  = new CellRangeAddress(49 + rows, 49 + rows, totalColumnas - 2*ancho + 1, totalColumnas - ancho);
			
			Cell cPerFirma2 = rPerFirma.createCell(totalColumnas - 2*ancho + 1);
			cPerFirma2.setCellValue("");
			cPerFirma2.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrPerFirma2);

	        CellRangeAddress mrPerFirma3  = new CellRangeAddress(49 + rows, 49 + rows, totalColumnas - ancho + 1, totalColumnas);
			
			Cell cPerFirma3 = rPerFirma.createCell(totalColumnas - ancho + 1);
			cPerFirma3.setCellValue("Ing. Lizbeth Revilla Leyva");
			cPerFirma3.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrPerFirma3);

	        Row rCarFirma = sheet.createRow(50 + rows);
	        CellRangeAddress mrCarFirma1  = new CellRangeAddress(50 + rows, 50 + rows, 0, ancho);
			
			Cell cCarFirma1 = rCarFirma.createCell(0);
			cCarFirma1.setCellValue("SUPERVISOR");
			cCarFirma1.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrCarFirma1);

	        CellRangeAddress mrCarFirma2  = new CellRangeAddress(50 + rows, 50 + rows, totalColumnas - 2*ancho + 1, totalColumnas - ancho);
			
			Cell cCarFirma2 = rCarFirma.createCell(totalColumnas - 2*ancho + 1);
			cCarFirma2.setCellValue("Residente de Obra");
			cCarFirma2.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrCarFirma2);

	        CellRangeAddress mrCarFirma3  = new CellRangeAddress(50 + rows, 50 + rows, totalColumnas - ancho + 1, totalColumnas);
			
			Cell cCarFirma3 = rCarFirma.createCell(totalColumnas - ancho + 1);
			cCarFirma3.setCellValue("Gerente de Planta");
			cCarFirma3.setCellStyle(centradoTitulo);

			sheet.addMergedRegion(mrCarFirma3);
			
		}
        
		FileOutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "archivo.xlsx"));
		workbook.write(outputStream);
        workbook.close();
        
	}

    private static void setCellBorderStyles(Workbook workbook, Sheet sheet, CellRangeAddress region, BorderStyle borderStyle) {
        for (int row = region.getFirstRow(); row <= region.getLastRow(); row++) {
            Row currentRow = sheet.getRow(row);
            if (currentRow == null) {
                currentRow = sheet.createRow(row);
            }

            for (int column = region.getFirstColumn(); column <= region.getLastColumn(); column++) {
                Cell currentCell = currentRow.getCell(column);
                if (currentCell == null) {
                    currentCell = currentRow.createCell(column);
                }
                
        		Font fontN = workbook.createFont();
        		fontN.setBold(true);
        		fontN.setFontHeightInPoints((short) 12);

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle.setFont(fontN);
                if(borderStyle!=null) {
                    cellStyle.setBorderTop(borderStyle);
                    cellStyle.setBorderRight(borderStyle);
                    cellStyle.setBorderBottom(borderStyle);
                    cellStyle.setBorderLeft(borderStyle);
                }

                currentCell.setCellStyle(cellStyle);
            }
        }
    }

    private static void setCellBorders(Workbook workbook, Cell cel, BorderStyle borderStyle, String[] settings, Short tFuente, String format) {
    	
    	CellStyle cellStyle = workbook.createCellStyle();;
    	Font fontN = workbook.createFont();
    	
    	if(format != null && format.equals("numero")) {
            cellStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
    	}
    	
    	if(format != null && format.equals("index")) {
            cellStyle.setDataFormat(workbook.createDataFormat().getFormat("00"));
    	}
    	
    	if(tFuente != null) {
    		fontN.setFontHeightInPoints(tFuente);
            cellStyle.setFont(fontN);
    	}
        
        if(Arrays.asList(settings).contains("hCenter")) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }
        
        if(Arrays.asList(settings).contains("vCenter")) {
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        
        if(Arrays.asList(settings).contains("negrita")) {
    		fontN.setFontHeightInPoints((short) 12);
            cellStyle.setFont(fontN);
        }
        
        if(Arrays.asList(settings).contains("sNegrita")) {
    		fontN.setFontHeightInPoints((short) 9);
            cellStyle.setFont(fontN);
        }
        
        if(Arrays.asList(settings).contains("bTop")) {
            cellStyle.setBorderTop(borderStyle);
        }
        
        if(Arrays.asList(settings).contains("bBottom")) {
            cellStyle.setBorderBottom(borderStyle);
        }
        
        if(Arrays.asList(settings).contains("bLeft")) {
            cellStyle.setBorderLeft(borderStyle);
        }
        
        if(Arrays.asList(settings).contains("bRight")) {
            cellStyle.setBorderRight(borderStyle);
        }
        
        if(Arrays.asList(settings).contains("rVertical")) {
            cellStyle.setRotation((short) 90);
        }
        
        if(Arrays.asList(settings).contains("bAll")) {
            cellStyle.setBorderTop(borderStyle);
            cellStyle.setBorderBottom(borderStyle);
            cellStyle.setBorderLeft(borderStyle);
            cellStyle.setBorderRight(borderStyle);
        }
        
        cel.setCellStyle(cellStyle);
    }

    private static void setCellBorderStylesSetting(Workbook workbook, Sheet sheet, CellRangeAddress region, BorderStyle borderStyle, String[] settings) {
        for (int row = region.getFirstRow(); row <= region.getLastRow(); row++) {
            Row currentRow = sheet.getRow(row);
            if (currentRow == null) {
                currentRow = sheet.createRow(row);
            }

            for (int column = region.getFirstColumn(); column <= region.getLastColumn(); column++) {
                Cell currentCell = currentRow.getCell(column);
                if (currentCell == null) {
                    currentCell = currentRow.createCell(column);
                }
                
        		Font fontN = workbook.createFont();
        		fontN.setBold(true);

                CellStyle cellStyle = workbook.createCellStyle();
                
                if(Arrays.asList(settings).contains("hCenter")) {
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                }
                
                if(Arrays.asList(settings).contains("vCenter")) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                }
                
                if(Arrays.asList(settings).contains("negrita")) {
            		fontN.setFontHeightInPoints((short) 12);
                    cellStyle.setFont(fontN);
                }
                
                if(Arrays.asList(settings).contains("sNegrita")) {
            		fontN.setFontHeightInPoints((short) 9);
                    cellStyle.setFont(fontN);
                }
                
                if(Arrays.asList(settings).contains("bTop")) {
                    cellStyle.setBorderTop(borderStyle);
                }
                
                if(Arrays.asList(settings).contains("bBottom")) {
                    cellStyle.setBorderBottom(borderStyle);
                }
                
                if(Arrays.asList(settings).contains("bLeft")) {
                    cellStyle.setBorderLeft(borderStyle);
                }
                
                if(Arrays.asList(settings).contains("bRight")) {
                    cellStyle.setBorderRight(borderStyle);
                }
                
                if(Arrays.asList(settings).contains("rVertical")) {
                    cellStyle.setRotation((short) 90);
                }
                
                if(Arrays.asList(settings).contains("bAll")) {
                    cellStyle.setBorderTop(borderStyle);
                    cellStyle.setBorderBottom(borderStyle);
                    cellStyle.setBorderLeft(borderStyle);
                    cellStyle.setBorderRight(borderStyle);
                }
                
                currentCell.setCellStyle(cellStyle);
            }
        }
    }
	
}