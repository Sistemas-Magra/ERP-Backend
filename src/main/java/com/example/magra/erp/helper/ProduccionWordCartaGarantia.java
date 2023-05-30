package com.example.magra.erp.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.example.magra.erp.VariablesGlobales;

public class ProduccionWordCartaGarantia {
	
	public static FileOutputStream generarCartaGarantia(Map<String, Object> datos, List<Map<String, Object>> productos, Integer tipoProducto) throws IOException, InvalidFormatException {
		
		//Map<String, Object> datosGenerales = productos.get(0);
		
		XWPFDocument document = new XWPFDocument();
		
		//Imagen Encabezado
		XWPFHeader encabezado = document.createHeader(HeaderFooterType.DEFAULT);
		XWPFParagraph pEncabezado = encabezado.createParagraph();
		XWPFRun pRun = pEncabezado.createRun();
		
		String imagePath = "C:/Proyecto/imgs/logo_carta_garantia.png";
		int format = XWPFDocument.PICTURE_TYPE_JPEG;
		int width = Units.toEMU(300);
		int height = Units.toEMU(50);
		pRun.addPicture(new FileInputStream(imagePath), format, "Nombre de la imagen", width, height);
		
		//titulo
		XWPFParagraph titulo = document.createParagraph();
		titulo.setAlignment(ParagraphAlignment.CENTER);
		
        XWPFRun tRun = titulo.createRun();
        
        tRun.setBold(true);
        tRun.setUnderline(UnderlinePatterns.SINGLE);
        tRun.setCapitalized(true);
        tRun.setText("CARTA DE GARANTÍA");
		
		//fecha
		XWPFParagraph fecha = document.createParagraph();
		fecha.setAlignment(ParagraphAlignment.RIGHT);
		
        XWPFRun fRun = fecha.createRun();
        
        fRun.setText(datos.get("departamento").toString() + ", " + datos.get("fecha").toString());
		
		//orden
		XWPFParagraph orden = document.createParagraph();
		
        XWPFRun oRun = orden.createRun();
        
        oRun.setBold(true);
        oRun.setUnderline(UnderlinePatterns.SINGLE);
        oRun.setText("MAGRA N° " + datos.get("orden_trabajo").toString());
		
		//ñores
		XWPFParagraph sres = document.createParagraph();
		
        XWPFRun sRun = sres.createRun();
        
        sRun.setText("Señor(es):");
		
		//cliente
		XWPFParagraph cliente = document.createParagraph();
		
        XWPFRun cRun = cliente.createRun();
        
        cRun.setText(datos.get("cliente").toString());
		
		//COMPRA
		XWPFParagraph compra = document.createParagraph();
		
        XWPFRun coRun = compra.createRun();
        
        coRun.setText("ORDEN DE COMPRA: " + datos.get("orden_venta").toString());
		
		//Obra
		XWPFParagraph obra = document.createParagraph();
		obra.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun obRun = obra.createRun();
        
        obRun.setText("OBRA “"+ datos.get("obra").toString() +"”.");
		
		//Parrafo 1
		XWPFParagraph parrafo1 = document.createParagraph();
		parrafo1.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p1Run = parrafo1.createRun();
        
        p1Run.setText("Por la presente garantizamos que los POSTES C.A.C. ofertados, nuevos y fabricados con material de alta calidad, según la Norma Técnica Peruana 339.027 – 2002, están en perfecto estado de conservación.\r\n"
        		+ "Esta garantía tiene una vigencia de " + (tipoProducto==1?"CINCO (05)":"DOS (02)") + " años de la fecha de Recepción de los Materiales y/o Equipos. Conforme a lo establecido en las especificaciones técnicas de:\r\n"
        		+ "");
        
        //Tabla de productos
        XWPFTable table = document.createTable(productos.size() + 1, 3);
        
        for (int row = 0; row < productos.size() + 1; row++) {
            for (int col = 0; col < 3; col++) {
                XWPFTableCell cell = table.getRow(row).getCell(col);
                if(row == 0) {
                	cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                	switch (col) {
                		case 0: {
                            cell.setText("Descripción");
                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
                            CTTblWidth cellWidth = tcPr.addNewTcW();
                            cellWidth.setType(STTblWidth.DXA);
                            cellWidth.setW(BigInteger.valueOf(2900));
                			break;
                		}
                		
                		case 1: {
                            cell.setText("Cantidad");
                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
                            CTTblWidth cellWidth = tcPr.addNewTcW();
                            cellWidth.setType(STTblWidth.DXA);
                            cellWidth.setW(BigInteger.valueOf(850));
                			break;
                		}
                		
                		case 2: {
                            cell.setText("Unidad");
                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
                            CTTblWidth cellWidth = tcPr.addNewTcW();
                            cellWidth.setType(STTblWidth.DXA);
                            cellWidth.setW(BigInteger.valueOf(850));
                			break;
                		}
                	}
                } else {
                	Map<String, Object> prod = productos.get(row - 1);
                	switch (col) {
	            		case 0: {
	                        cell.setText(prod.get("producto").toString());
	                        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
	                        CTTblWidth cellWidth = tcPr.addNewTcW();
	                        cellWidth.setType(STTblWidth.DXA);
	                        cellWidth.setW(BigInteger.valueOf(2900));
	            			break;
	            		}
	            		
	            		case 1: {
	                        cell.setText(prod.get("cantidad").toString());
	                        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
	                        CTTblWidth cellWidth = tcPr.addNewTcW();
	                        cellWidth.setType(STTblWidth.DXA);
	                        cellWidth.setW(BigInteger.valueOf(850));
	                    	cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
	            			break;
	            		}
	            		
	            		case 2: {
	                        cell.setText("UND");
	                        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
	                        CTTblWidth cellWidth = tcPr.addNewTcW();
	                        cellWidth.setType(STTblWidth.DXA);
	                        cellWidth.setW(BigInteger.valueOf(850));
	                    	cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
	            			break;
	            		}
	            	}
                }
                
                CTTcBorders borders = cell.getCTTc().addNewTcPr().addNewTcBorders();
                borders.addNewTop().setSz(BigInteger.valueOf(1));
                borders.addNewBottom().setSz(BigInteger.valueOf(1));
                borders.addNewLeft().setSz(BigInteger.valueOf(1));
                borders.addNewRight().setSz(BigInteger.valueOf(1));

            }
        }
		
		//Parrafo 2
		XWPFParagraph parrafo2 = document.createParagraph();
		parrafo2.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p2Run = parrafo2.createRun();
        
        p2Run.setText("Durante este período de tiempo nos comprometemos a reemplazar, sin costo alguno para su representada; "
        		+ "los Postes que resulten con defectos de fabricación o calidad, por otros que cumplan satisfactoriamente las Especificaciones Técnicas.");
		
		//Parrafo 3
		XWPFParagraph parrafo3 = document.createParagraph();
		parrafo3.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p3Run = parrafo3.createRun();
        
        p3Run.setText("En este caso, el período de garantía de los repuestos o partes entregados en reemplazo, tendrá igualmente la vigencia establecida líneas arriba. Con este fin, subsanaremos cualquier deficiencia imputable a nosotros.\r\n"
        		+ "Esta garantía cubre únicamente los defectos producidos durante el proceso de su fabricación y operación en condiciones normales, no así los ocasionados por manipulaciones indebidas u otras causas ajenas a nuestra responsabilidad\r\n"
        		+ "");
        
		//Parrafo 4
		XWPFParagraph parrafo4 = document.createParagraph();
		parrafo4.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p4Run = parrafo4.createRun();
        
        p4Run.setText("Atentamente,");
		
		//Guardado del archivo
		FileOutputStream outputStream = new FileOutputStream(new File(VariablesGlobales.CARTAS_GARANTIA + "/" + datos.get("ord_venta").toString() + "-" + datos.get("cliente").toString() + "-tp" + tipoProducto + ".docx"));
        document.write(outputStream);
        outputStream.close();
        document.close();
        
		return outputStream;
		
	}

}