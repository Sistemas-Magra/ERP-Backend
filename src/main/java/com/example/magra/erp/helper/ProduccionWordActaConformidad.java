package com.example.magra.erp.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.example.magra.erp.VariablesGlobales;

public class ProduccionWordActaConformidad {
	
	public static FileOutputStream generarActaConformidad(Map<String, Object> datos, List<Map<String, Object>> productos) throws IOException {
		
		XWPFDocument document = new XWPFDocument();
		
		//titulo
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
        XWPFRun run = paragraph.createRun();
        
        run.setBold(true);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setCapitalized(true);
        run.setText("ACTA DE RECEPCION Y CONFORMIDAD");
        
        //primer parrafo
		XWPFParagraph paragraph2 = document.createParagraph();
		paragraph2.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun run2 = paragraph2.createRun();
        
        run2.setText("En la planta de COMPAÑÍA MAGRA SAC, en la ciudad de Lima, siendo las " + datos.get("fechaInicio").toString() +", Gerente de Planta la Ing. Lisbet Revilla Leyva "
        		+ "por parte del proveedor de COMPAÑÍA MAGRA SAC.; se realizó el control de calidad de los Postes de Concreto Centrifugado para "
        		+ datos.get("cliente").toString());
        
        //segundo parrafo
		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun run3 = paragraph3.createRun();
        
        run3.setText("Teniendo como nombre “" + datos.get("obra").toString() + "”");
        
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
        
        //tercer parrafo
		XWPFParagraph paragraph4 = document.createParagraph();
		paragraph4.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun run4 = paragraph4.createRun();
        
        run4.setText("Efectuada la inspección visual, verificación de dimensiones, recubrimientos mínimos permitidos y revisión de estructuras internas;"
        		+ " encontrándose todas las pruebas conformes de acuerdo a las especificaciones técnicas de fabricación, según la Normas Técnicas Peruanas"
        		+ " NTP 339.027 -2002. Se acordó declarar conforme el lote de Postes C.A.C. presentados en cumplimiento con la norma,"
        		+ " y se encuentran aptos para su traslado.");

        //cuarto parrafo
		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.BOTH);
		
        XWPFRun run5 = paragraph5.createRun();
        
        run5.setText("Siendo las "+ datos.get("fechaFin").toString() + " se dio por concluido el control de calidad y firman, en señal de conformidad");

        //Tabla de personas
        XWPFTable table2 = document.createTable(4, 2);
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 2; col++) {
                XWPFTableCell cell = table2.getRow(row).getCell(col);
            	cell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                
                switch(row) {
                	case 0: {
                		switch(col) {
                			case 0: {
                                cell.setText("COMPAÑÍA MAGRA S.A.C.");
                                CTTcPr tcPr = cell.getCTTc().addNewTcPr();
                                CTTblWidth cellWidth = tcPr.addNewTcW();
                                cellWidth.setType(STTblWidth.DXA);
                                cellWidth.setW(BigInteger.valueOf(6000));
                				
                				break;
                			}

                			case 1: {
                                cell.setText(datos.get("cliente").toString());
                                CTTcPr tcPr = cell.getCTTc().addNewTcPr();
                                CTTblWidth cellWidth = tcPr.addNewTcW();
                                cellWidth.setType(STTblWidth.DXA);
                                cellWidth.setW(BigInteger.valueOf(6000));
                				break;
                			}
                		}
                		break;
                	}
                	case 1: {
                		switch(col) {
	            			case 0: {
	                            cell.setText("_____________________________");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				cell.setVerticalAlignment(XWPFVertAlign.BOTTOM);
	            				break;
	            			}
	
	            			case 1: {
	                            cell.setText("_____________________________");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				cell.setVerticalAlignment(XWPFVertAlign.BOTTOM);
	            				break;
	            			}
	            		}
                		XWPFTableRow trow = table2.getRow(row);
                		trow.setHeight(2500);
                		break;
                	}
                	
                	case 2: {
                		switch(col) {
	            			case 0: {
	                            cell.setText("Ing. Lisbet Revilla Leyva");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				
	            				break;
	            			}
	
	            			case 1: {
	                            cell.setText("");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				break;
	            			}
	            		}
                		
                		break;
                	}

                	case 3: {
                		switch(col) {
	            			case 0: {
	                            cell.setText("Gerente de Planta");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				
	            				break;
	            			}
	
	            			case 1: {
	                            cell.setText("");
	                            CTTcPr tcPr = cell.getCTTc().addNewTcPr();
	                            CTTblWidth cellWidth = tcPr.addNewTcW();
	                            cellWidth.setType(STTblWidth.DXA);
	                            cellWidth.setW(BigInteger.valueOf(6000));
	            				break;
	            			}
	            		}
                		
                		break;
                	}
                }
                
                CTTcBorders borders = cell.getCTTc().addNewTcPr().addNewTcBorders();
                borders.addNewTop().setSz(BigInteger.valueOf(1));
                borders.addNewBottom().setSz(BigInteger.valueOf(1));
                borders.addNewLeft().setSz(BigInteger.valueOf(1));
                borders.addNewRight().setSz(BigInteger.valueOf(1));
            }
        }
        
		FileOutputStream outputStream = new FileOutputStream(new File(VariablesGlobales.ACTA_CONFORMIDAD + "/" + datos.get("orden_venta").toString() + "-" + datos.get("cliente").toString() + ".docx"));
        document.write(outputStream);
        outputStream.close();
        document.close();
        
		return outputStream;
		
	}
}