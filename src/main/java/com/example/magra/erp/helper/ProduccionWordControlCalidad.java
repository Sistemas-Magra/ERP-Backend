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

public class ProduccionWordControlCalidad {

	public static FileOutputStream generarControlCalidad(Map<String, Object> datos, List<Map<String, Object>> productos) throws IOException, InvalidFormatException {
		
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
        oRun.setText("MAGRA N° " + datos.get("codigo").toString());
		
		//ñores
		XWPFParagraph sres = document.createParagraph();
		
        XWPFRun sRun = sres.createRun();
        
        sRun.setText("Señor(es):");
		
		//cliente
		XWPFParagraph cliente = document.createParagraph();
		
        XWPFRun cRun = cliente.createRun();
        
        cRun.setText(datos.get("cliente").toString());
		
		//asunto
		XWPFParagraph asunto = document.createParagraph();
		
        XWPFRun aRun = asunto.createRun();
        
        aRun.setText("Asunto: CONTROL DE CALIDAD");
		
		//consideracion
		XWPFParagraph consideracion = document.createParagraph();
		
        XWPFRun coRun = consideracion.createRun();
        
        coRun.setText("De nuestra consideración:");
		
		//parrafo 2
		XWPFParagraph parrafo2 = document.createParagraph();
		parrafo2.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p2Run = parrafo2.createRun();
        
        p2Run.setText("Por medio de la presente le hacemos llegar nuestros saludos cordiales y aprovechamos la oportunidad,"
        		+ " para comunicarle que sobre la Control de Calidad para los siguientes productos:");
        
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
                            cellWidth.setW(BigInteger.valueOf(4000));
                			break;
                		}
                		
                		case 1: {
                            cell.setText("Cantidad");
                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
                            CTTblWidth cellWidth = tcPr.addNewTcW();
                            cellWidth.setType(STTblWidth.DXA);
                            cellWidth.setW(BigInteger.valueOf(1000));
                			break;
                		}
                		
                		case 2: {
                            cell.setText("Unidad");
                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
                            CTTblWidth cellWidth = tcPr.addNewTcW();
                            cellWidth.setType(STTblWidth.DXA);
                            cellWidth.setW(BigInteger.valueOf(1000));
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
	                        cellWidth.setW(BigInteger.valueOf(4000));
	            			break;
	            		}
	            		
	            		case 1: {
	                        cell.setText(prod.get("cantidad").toString());
	                        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
	                        CTTblWidth cellWidth = tcPr.addNewTcW();
	                        cellWidth.setType(STTblWidth.DXA);
	                        cellWidth.setW(BigInteger.valueOf(1000));
	                    	cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
	            			break;
	            		}
	            		
	            		case 2: {
	                        cell.setText("UND");
	                        CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                        
	                        CTTblWidth cellWidth = tcPr.addNewTcW();
	                        cellWidth.setType(STTblWidth.DXA);
	                        cellWidth.setW(BigInteger.valueOf(1000));
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
		
		//parrafo 3
		XWPFParagraph parrafo3 = document.createParagraph();
		parrafo3.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p3Run = parrafo3.createRun();
        
        p3Run.setText("Se llevarán a cabo el día " + datos.get("fechaCita").toString() + ", para tal efecto sírvanse asignar un representante.");
		
		//parrafo 4
		XWPFParagraph parrafo4 = document.createParagraph();
		parrafo4.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p4Run = parrafo4.createRun();
        
        p4Run.setText("Todas las coordinaciones dirigirse con la Ing. Lisbet Revilla Leyva, nuestros teléfonos de contacto son: (01) 527 4267, 940 317 839, 950 821 190.");
		
		//parrafo 5
		XWPFParagraph parrafo5 = document.createParagraph();
		parrafo5.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p5Run = parrafo5.createRun();
        
        p5Run.setText("Enviar con 24hras. De antelación los siguientes documentos, DNI y SCTR. Al ingresar a los almacenes deben contar con sus implementos de seguridad.");
		
		//parrafo 6
		XWPFParagraph parrafo6 = document.createParagraph();
		parrafo6.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p6Run = parrafo6.createRun();
        
        p6Run.setText("Sin otro en particular y agradeciéndoles la atención prestada,");
		
		//parrafo 7
		XWPFParagraph parrafo7 = document.createParagraph();
		parrafo7.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun p7Run = parrafo7.createRun();
        
        p7Run.setText("Atentamente,");
		
		//Guardado del archivo
		FileOutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "archivo3.docx"));
        document.write(outputStream);
        outputStream.close();
        document.close();
        
		return outputStream;
		
	}
}