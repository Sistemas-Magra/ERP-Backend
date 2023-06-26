package com.example.magra.erp.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.example.magra.erp.VariablesGlobales;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class DespachoPdfGuiRemision {
	public static void generarGuiaRemision(Map<String, Object> datosFormulario, List<Map<String, Object>> detalle) throws DocumentException, MalformedURLException, IOException {
		Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(VariablesGlobales.GUIAS_REMISION + "/DESPACHO " + datosFormulario.get("remision").toString() + ".pdf"));
        document.open();
        
        //Normales
        Font fontH7 = new Font(Font.FontFamily.HELVETICA, 7);
        
        //Negritas
        Font fontH7B = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
        Font fontH9B = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
        Font fontH11B = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        
        //Colores
        BaseColor fondoNaranja = new BaseColor(255,216,88);
        
        PdfPTable tHeader = new PdfPTable(new float[]{1,1,1});
        tHeader.setWidthPercentage(100);
        tHeader.setSpacingBefore(5f);
        tHeader.setSpacingAfter(5f);
        
        PdfPCell cImg = new PdfPCell();
        Image image = Image.getInstance(VariablesGlobales.IMAGENES + "/logo.png");
        image.scalePercent(14f);
        
        cImg.addElement(image);
        cImg.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cImg.setBorderWidth(0);
        
        PdfPCell cDireccion= new PdfPCell();
        Paragraph direccion = new Paragraph("Oficina Principal\n"
        		+ "MZA. K LOTE 138 LEONCIO PRADO OESTE (KM. 34 PANAM. NORTE PARADERO FUNDICIÓN)\n"
        		+ "PUENTE PIEDRA - LIMA - LIMA", fontH7);
        direccion.setAlignment(Element.ALIGN_CENTER);
        
        cDireccion.setHorizontalAlignment(Element.ALIGN_CENTER);
        cDireccion.setBorderWidth(0);
        cDireccion.addElement(direccion);
        
        PdfPCell cSubTable= new PdfPCell();
        PdfPTable tSubTable = new PdfPTable(new float[]{1});
        PdfPCell c1= new PdfPCell();
        Paragraph ruc = new Paragraph("R.U.C. N° 20551613217\n"
        		+ "GUIA DE REMISION\n"
        		+ "ELECTRONICA - REMITENTE\n"
        		+ datosFormulario.get("remision").toString(), fontH11B);
        ruc.setAlignment(Element.ALIGN_CENTER);
        
        c1.addElement(ruc);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_TOP);
        c1.setBorderWidth(0);
        
        c1.setPaddingBottom(20f);
        cSubTable.setRowspan(2);
        
        tSubTable.setWidthPercentage(100);
        tSubTable.addCell(c1);
        cSubTable.addElement(tSubTable);
        cSubTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell cNombreCompleto= new PdfPCell();
        Paragraph nombreCompleto = new Paragraph("COMPAÑIA MAGRA SOCIEDAD\r\n"
        		+ "ANONIMA CERRADA", fontH9B);
        
        cNombreCompleto.addElement(nombreCompleto);
        cNombreCompleto.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cNombreCompleto.setBorderWidth(0);
        
        PdfPCell cVacio= new PdfPCell();
        Paragraph vacio = new Paragraph("");
        
        cVacio.addElement(vacio);
        cVacio.setBorderWidth(0);
        
        tHeader.addCell(cImg);
        tHeader.addCell(cDireccion);
        tHeader.addCell(cSubTable);
        tHeader.addCell(cNombreCompleto);
        tHeader.addCell(cVacio);
        
        PdfPTable tDatosDestino= new PdfPTable(new float[]{1.7f, 3.83f, 1, 3.83f});
        tDatosDestino.setWidthPercentage(100);
        tDatosDestino.setSpacingBefore(5f);
        tDatosDestino.setSpacingAfter(5f);
        
        PdfPCell clCliente= new PdfPCell();
        Paragraph lCliente = new Paragraph("Destinatario", fontH7B);
        clCliente.setBorder(Rectangle.LEFT | Rectangle.TOP);
        clCliente.addElement(lCliente);
        tDatosDestino.addCell(clCliente);
        
        PdfPCell cvCliente= new PdfPCell();
        Paragraph vCliente = new Paragraph(": " + datosFormulario.get("cliente").toString(), fontH7);
        cvCliente.setBorder(Rectangle.RIGHT | Rectangle.TOP);
        cvCliente.addElement(vCliente);
        cvCliente.setColspan(3);
        tDatosDestino.addCell(cvCliente);
        
        PdfPCell clDireccion = new PdfPCell();
        Paragraph lDireccion = new Paragraph("Dirección", fontH7B);
        clDireccion.setBorder(Rectangle.LEFT);
        clDireccion.addElement(lDireccion);
        tDatosDestino.addCell(clDireccion);
        
        PdfPCell cvDireccion = new PdfPCell();
        Paragraph vDireccion = new Paragraph(": " + datosFormulario.get("direccion").toString(), fontH7);
        cvDireccion.setBorder(Rectangle.RIGHT);
        cvDireccion.addElement(vDireccion);
        cvDireccion.setColspan(3);
        tDatosDestino.addCell(cvDireccion);
        
        PdfPCell clRuc = new PdfPCell();
        Paragraph lRuc = new Paragraph("R.U.C. N°", fontH7B);
        clRuc.setBorder(Rectangle.LEFT);
        clRuc.addElement(lRuc);
        tDatosDestino.addCell(clRuc);
        
        PdfPCell cvRuc = new PdfPCell();
        Paragraph vRuc = new Paragraph(": " + datosFormulario.get("ruc").toString(), fontH7);
        cvRuc.setBorder(Rectangle.RIGHT);
        cvRuc.addElement(vRuc);
        cvRuc.setColspan(3);
        tDatosDestino.addCell(cvRuc);
        
        PdfPCell clPtoPartida = new PdfPCell();
        Paragraph lPtoPartida = new Paragraph("Pto. Partida", fontH7B);
        clPtoPartida.setBorder(Rectangle.LEFT);
        clPtoPartida.addElement(lPtoPartida);
        tDatosDestino.addCell(clPtoPartida);
        
        PdfPCell cvlPtoPartida= new PdfPCell();
        Paragraph vPtoPartida= new Paragraph(": " + datosFormulario.get("partida").toString(), fontH7);
        cvlPtoPartida.setBorder(Rectangle.RIGHT);
        cvlPtoPartida.addElement(vPtoPartida);
        cvlPtoPartida.setColspan(3);
        tDatosDestino.addCell(cvlPtoPartida);
        
        PdfPCell clPtoLlegada= new PdfPCell();
        Paragraph lPtoLlegada= new Paragraph("Pto. Llegada", fontH7B);
        clPtoLlegada.setBorder(Rectangle.LEFT);
        clPtoLlegada.addElement(lPtoLlegada);
        tDatosDestino.addCell(clPtoLlegada);
        
        PdfPCell cvPtoLlegada = new PdfPCell();
        Paragraph vPtoLlegada = new Paragraph(": " + datosFormulario.get("destino").toString(), fontH7);
        cvPtoLlegada.setBorder(Rectangle.RIGHT);
        cvPtoLlegada.addElement(vPtoLlegada);
        cvPtoLlegada.setColspan(3);
        tDatosDestino.addCell(cvPtoLlegada);
        
        PdfPCell clFechaEmision = new PdfPCell();
        Paragraph lFechaEmision = new Paragraph("Fecha Emisión", fontH7B);
        clFechaEmision.setBorder(Rectangle.LEFT);
        clFechaEmision.addElement(lFechaEmision);
        tDatosDestino.addCell(clFechaEmision);
        
        PdfPCell cvFechaEmision = new PdfPCell();
        Paragraph vFechasEmision = new Paragraph(": " + datosFormulario.get("f_emision").toString(), fontH7);
        cvFechaEmision.setBorder(Rectangle.NO_BORDER);
        cvFechaEmision.addElement(vFechasEmision);
        tDatosDestino.addCell(cvFechaEmision);
        
        PdfPCell clFechaPartida = new PdfPCell();
        Paragraph lFechaPartida = new Paragraph("Fecha Partida", fontH7B);
        clFechaPartida.setBorder(Rectangle.NO_BORDER);
        clFechaPartida.addElement(lFechaPartida);
        tDatosDestino.addCell(clFechaPartida);
        
        PdfPCell cvFechaPartida = new PdfPCell();
        Paragraph vFechaPartida = new Paragraph(": " + datosFormulario.get("f_partida").toString(), fontH7);
        cvFechaPartida.setBorder(Rectangle.RIGHT);
        cvFechaPartida.addElement(vFechaPartida);
        tDatosDestino.addCell(cvFechaPartida);
        
        PdfPCell clMotivo = new PdfPCell();
        Phrase lMotivo = new Phrase("Motivo del Traslado", fontH7B);
        clMotivo.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
        clMotivo.addElement(lMotivo);
        tDatosDestino.addCell(clMotivo);
        
        PdfPCell cvMotivo = new PdfPCell();
        Phrase vMotivo = new Phrase(": " + datosFormulario.get("motivo_traslado").toString(), fontH7);
        cvMotivo.setBorder(Rectangle.BOTTOM);
        cvMotivo.addElement(vMotivo);
        tDatosDestino.addCell(cvMotivo);
        
        PdfPCell clModalidad = new PdfPCell();
        Phrase lModalidad = new Phrase("Usuario", fontH7B);
        clModalidad.setBorder(Rectangle.BOTTOM);
        clModalidad.addElement(lModalidad);
        tDatosDestino.addCell(clModalidad);
        
        PdfPCell cvModalidad = new PdfPCell();
        Phrase vModalidad = new Phrase(": " + datosFormulario.get("usuario").toString(), fontH7);
        cvModalidad.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
        cvModalidad.addElement(vModalidad);
        tDatosDestino.addCell(cvModalidad);
        
        PdfPTable tDetalle = new PdfPTable(new float[]{1, 3, 13, 1.5f, 2, 2, 2.2f});
        tDetalle.setWidthPercentage(100);
        tDetalle.setSpacingBefore(5f);
        tDetalle.setSpacingAfter(5f);
        
        PdfPCell cH1= new PdfPCell();
        Paragraph h1 = new Paragraph("IT", fontH7);
        h1.setAlignment(Element.ALIGN_CENTER);
        cH1.addElement(h1);
        cH1.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
        cH1.setBackgroundColor(fondoNaranja);
        cH1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH1);
        
        PdfPCell cH2= new PdfPCell();
        Paragraph h2 = new Paragraph("CODIGO", fontH7);
        h2.setAlignment(Element.ALIGN_CENTER);
        cH2.addElement(h2);
        cH2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cH2.setBackgroundColor(fondoNaranja);
        cH2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH2);
        
        PdfPCell cH3 = new PdfPCell();
        Paragraph h3 = new Paragraph("DESCRIPCION", fontH7);
        h3.setAlignment(Element.ALIGN_CENTER);
        cH3.addElement(h3);
        cH3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cH3.setBackgroundColor(fondoNaranja);
        cH3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH3);
        
        PdfPCell cH4 = new PdfPCell();
        Paragraph h4 = new Paragraph("U.M", fontH7);
        h4.setAlignment(Element.ALIGN_CENTER);
        cH4.addElement(h4);
        cH4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cH4.setBackgroundColor(fondoNaranja);
        cH4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH4);
        
        PdfPCell cH5 = new PdfPCell();
        Paragraph h5 = new Paragraph("CANTIDAD", fontH7);
        h5.setAlignment(Element.ALIGN_CENTER);
        cH5.addElement(h5);
        cH5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cH5.setBackgroundColor(fondoNaranja);
        cH5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH5);
        
        PdfPCell cH6 = new PdfPCell();
        Paragraph h6 = new Paragraph("PESO UNIT.", fontH7);
        h6.setAlignment(Element.ALIGN_CENTER);
        cH6.addElement(h6);
        cH6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        cH6.setBackgroundColor(fondoNaranja);
        cH6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH6);
        
        PdfPCell cH7 = new PdfPCell();
        Paragraph h7 = new Paragraph("PESO DESPACHO", fontH7);
        h7.setAlignment(Element.ALIGN_CENTER);
        cH7.addElement(h7);
        cH7.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
        cH7.setBackgroundColor(fondoNaranja);
        cH7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDetalle.addCell(cH7);
    	
    	int indexInicio = 0, indexMedio = 0, indexFinal = 0;
    	
		indexInicio = Rectangle.LEFT;
		indexMedio = Rectangle.NO_BORDER;
		indexFinal = Rectangle.RIGHT;
		
		float cellHeight = 15;
        
        for (Map<String, Object> det: detalle) {
            
            PdfPCell cdH1= new PdfPCell();
            Paragraph hd1 = new Paragraph((detalle.indexOf(det) + 1) + "", fontH7);
            hd1.setAlignment(Element.ALIGN_CENTER);
            cdH1.addElement(hd1);
            cdH1.setBorder(indexInicio);
            cdH1.setFixedHeight(cellHeight);
            cdH1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH1);
            
            PdfPCell cdH2= new PdfPCell();
            Paragraph hd2 = new Paragraph(det.get("codigo").toString(), fontH7);
            hd2.setAlignment(Element.ALIGN_CENTER);
            cdH2.addElement(hd2);
            cdH2.setBorder(indexMedio);
            cdH2.setFixedHeight(cellHeight);
            cdH2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH2);
            
            PdfPCell cdH3 = new PdfPCell();
            Paragraph hd3 = new Paragraph(det.get("producto").toString(), fontH7);
            cdH3.addElement(hd3);
            cdH3.setBorder(indexMedio);
            cdH3.setFixedHeight(cellHeight);
            cdH3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH3);
            
            PdfPCell cdH4 = new PdfPCell();
            Paragraph hd4 = new Paragraph("UND", fontH7);
            hd4.setAlignment(Element.ALIGN_CENTER);
            cdH4.addElement(hd4);
            cdH4.setBorder(indexMedio);
            cdH4.setFixedHeight(cellHeight);
            cdH4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH4);
            
            PdfPCell cdH5 = new PdfPCell();
            Paragraph hd5 = new Paragraph(det.get("cantidad").toString(), fontH7);
            hd5.setAlignment(Element.ALIGN_CENTER);
            cdH5.addElement(hd5);
            cdH5.setBorder(indexMedio);
            cdH5.setFixedHeight(cellHeight);
            cdH5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH5);
            
            PdfPCell cdH6 = new PdfPCell();
            Paragraph hd6 = new Paragraph(det.get("peso_unitario").toString(), fontH7);
            hd6.setAlignment(Element.ALIGN_CENTER);
            cdH6.addElement(hd6);
            cdH6.setBorder(indexMedio);
            cdH6.setFixedHeight(cellHeight);
            cdH6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH6);
            
            PdfPCell cdH7 = new PdfPCell();
            Paragraph hd7 = new Paragraph(det.get("peso_despacho").toString(), fontH7);
            hd7.setAlignment(Element.ALIGN_CENTER);
            cdH7.addElement(hd7);
            cdH7.setBorder(indexFinal);
            cdH7.setFixedHeight(cellHeight);
            cdH7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tDetalle.addCell(cdH7);
        	
        }
		indexInicio =  Rectangle.LEFT | Rectangle.BOTTOM;
		indexMedio =  Rectangle.BOTTOM;
		indexFinal =  Rectangle.RIGHT | Rectangle.BOTTOM;
        
        int numRows = 19 - tDetalle.getRows().size();
        
        float remainingHeight = cellHeight * numRows;
        
        PdfPCell lastRow1 = new PdfPCell(new Phrase(""));
        lastRow1.setBorder(indexInicio);
        lastRow1.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow1);
        
        PdfPCell lastRow2 = new PdfPCell(new Phrase(""));
        lastRow2.setBorder(indexMedio);
        lastRow2.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow2);
        
        PdfPCell lastRow3 = new PdfPCell(new Phrase(""));
        lastRow3.setBorder(indexMedio);
        lastRow3.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow3);
        
        PdfPCell lastRow4 = new PdfPCell(new Phrase(""));
        lastRow4.setBorder(indexMedio);
        lastRow4.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow4);
        
        PdfPCell lastRow5 = new PdfPCell(new Phrase(""));
        lastRow5.setBorder(indexMedio);
        lastRow5.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow5);
        
        PdfPCell lastRow6 = new PdfPCell(new Phrase(""));
        lastRow6.setBorder(indexMedio);
        lastRow6.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow6);
        
        PdfPCell lastRow7 = new PdfPCell(new Phrase(""));
        lastRow7.setBorder(indexFinal);
        lastRow7.setMinimumHeight(remainingHeight);
        tDetalle.addCell(lastRow7);
        
        PdfPTable tDatosFirmas= new PdfPTable(new float[]{1,1,1,1});
        tDatosFirmas.setWidthPercentage(100);
        tDatosFirmas.setSpacingBefore(5f);
        tDatosFirmas.setSpacingAfter(5f);
        
        PdfPTable tDatosTransporte= new PdfPTable(new float[]{2.89f, 3.5f, 1, 2.44f});
        tDatosTransporte.setWidthPercentage(100);
        tDatosTransporte.setSpacingBefore(5f);
        tDatosTransporte.setSpacingAfter(5f);
        
        PdfPCell clTransportista = new PdfPCell(new Phrase("EMPRESA TRANSPORTE", fontH7B));
        clTransportista.setFixedHeight(20);
        clTransportista.setVerticalAlignment(Element.ALIGN_MIDDLE);
        clTransportista.setBorder(Rectangle.LEFT | Rectangle.TOP);
        tDatosTransporte.addCell(clTransportista);
        
        PdfPCell cvTransportista = new PdfPCell(new Phrase(": " + datosFormulario.get("empresa_transporte").toString(), fontH7));
        cvTransportista.setColspan(3);
        cvTransportista.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cvTransportista.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        tDatosTransporte.addCell(cvTransportista);
        
        PdfPCell clRucTransp = new PdfPCell(new Phrase("R.U.C.", fontH7B));
        clRucTransp.setFixedHeight(20);
        clRucTransp.setVerticalAlignment(Element.ALIGN_MIDDLE);
        clRucTransp.setBorder(Rectangle.LEFT);
        tDatosTransporte.addCell(clRucTransp);
        
        PdfPCell cvRucTransp = new PdfPCell(new Phrase(": " + datosFormulario.get("ruc_transporte").toString(), fontH7));
        cvRucTransp.setColspan(3);
        cvRucTransp.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cvRucTransp.setBorder(Rectangle.RIGHT);
        tDatosTransporte.addCell(cvRucTransp);
        
        PdfPCell clChofer = new PdfPCell(new Phrase("CHOFER", fontH7B));
        clChofer.setFixedHeight(20);
        clChofer.setVerticalAlignment(Element.ALIGN_MIDDLE);
        clChofer.setBorder(Rectangle.LEFT);
        tDatosTransporte.addCell(clChofer);
        
        PdfPCell cvChofer = new PdfPCell(new Phrase(": " + datosFormulario.get("conductor").toString(), fontH7));
        cvChofer.setBorder(Rectangle.NO_BORDER);
        cvChofer.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(cvChofer);
        
        PdfPCell clBrevete = new PdfPCell(new Phrase("LICENCIA", fontH7B));
        clBrevete.setBorder(Rectangle.NO_BORDER);
        clBrevete.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(clBrevete);
        
        PdfPCell cvBrevete = new PdfPCell(new Phrase(": " + datosFormulario.get("licencia").toString(), fontH7));
        cvBrevete.setBorder(Rectangle.RIGHT);
        cvBrevete.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(cvBrevete);
        
        PdfPCell clMarca = new PdfPCell(new Phrase("MARCA VEHICULO", fontH7B));
        clMarca.setFixedHeight(20);
        clMarca.setVerticalAlignment(Element.ALIGN_MIDDLE);
        clMarca.setBorder(Rectangle.LEFT);
        tDatosTransporte.addCell(clMarca);
        
        PdfPCell cvMarca = new PdfPCell(new Phrase(": -", fontH7));
        cvMarca.setBorder(Rectangle.NO_BORDER);
        cvMarca.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(cvMarca);
        
        PdfPCell clPlaca = new PdfPCell(new Phrase("PLACA", fontH7B));
        clPlaca.setBorder(Rectangle.NO_BORDER);
        clPlaca.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(clPlaca);
        
        PdfPCell cvPlaca= new PdfPCell(new Phrase(": " + datosFormulario.get("placa").toString(), fontH7));
        cvPlaca.setBorder(Rectangle.RIGHT);
        cvPlaca.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tDatosTransporte.addCell(cvPlaca);
        
        PdfPCell clConstancia = new PdfPCell(new Phrase("CONSTANCIA DE INSCRIPCIÓN", fontH7B));
        clConstancia.setFixedHeight(20);
        clConstancia.setVerticalAlignment(Element.ALIGN_MIDDLE);
        clConstancia.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
        tDatosTransporte.addCell(clConstancia);
        
        PdfPCell cvConstancia = new PdfPCell(new Phrase(":", fontH7));
        cvConstancia.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
        cvConstancia.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cvConstancia.setColspan(3);
        tDatosTransporte.addCell(cvConstancia);
        
        PdfPCell cDatosTransporte = new PdfPCell();
        cDatosTransporte.setBorder(Rectangle.NO_BORDER);
        cDatosTransporte.setColspan(3);
        cDatosTransporte.setPadding(0);
        cDatosTransporte.addElement(tDatosTransporte);
        tDatosFirmas.addCell(cDatosTransporte);
        
        PdfPCell cFirma1 = new PdfPCell();
        Paragraph vFirma1 = new Paragraph("_____________________________\nRECIBÍ CONFORME\nCLIENTE", fontH7);
        vFirma1.setAlignment(Element.ALIGN_CENTER);
        cFirma1.setBorder(Rectangle.NO_BORDER);
        cFirma1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cFirma1.addElement(vFirma1);
        tDatosFirmas.addCell(cFirma1);
        
        PdfPCell cFirma2 = new PdfPCell();
        Paragraph vFirma2 = new Paragraph("_____________________________\nVENTAS\n ", fontH7);
        vFirma2.setAlignment(Element.ALIGN_CENTER);
        cFirma2.setBorder(Rectangle.NO_BORDER);
        cFirma2.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cFirma2.setFixedHeight(60);
        cFirma2.addElement(vFirma2);
        tDatosFirmas.addCell(cFirma2);
        
        PdfPCell cFirma3 = new PdfPCell();
        Paragraph vFirma3 = new Paragraph("_____________________________\nRECIBÍ CONFORME\nTRANSPORTISTA", fontH7);
        vFirma3.setAlignment(Element.ALIGN_CENTER);
        cFirma3.setBorder(Rectangle.NO_BORDER);
        cFirma3.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cFirma3.addElement(vFirma3);
        tDatosFirmas.addCell(cFirma3);
        
        PdfPCell cFirma4 = new PdfPCell();
        Paragraph vFirma4 = new Paragraph("_____________________________\nALMACÉN\n ", fontH7);
        vFirma4.setAlignment(Element.ALIGN_CENTER);
        cFirma4.setBorder(Rectangle.NO_BORDER);
        cFirma4.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cFirma4.addElement(vFirma4);
        tDatosFirmas.addCell(cFirma4);
        
        PdfPCell cFirma5 = new PdfPCell();
        Paragraph vFirma5 = new Paragraph("_____________________________\nVIGILANCIA\n ", fontH7);
        vFirma5.setAlignment(Element.ALIGN_CENTER);
        cFirma5.setBorder(Rectangle.NO_BORDER);
        cFirma5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cFirma5.addElement(vFirma5);
        tDatosFirmas.addCell(cFirma5);
        
        String codigo = "20551613217|T001|00000670|13-06-23|";
        int qrCodeSize = 200;
        
        BitMatrix bitMatrix = generateQRCode(codigo, qrCodeSize);
        
        Image qrCodeImage = createQRCodeImage(bitMatrix, qrCodeSize);
        qrCodeImage.scalePercent(25);
        PdfPTable tQR = new PdfPTable(1);
        tQR.setWidthPercentage(100);
        tQR.setSpacingBefore(5f);
        tQR.setSpacingAfter(5f);

        PdfPCell cQR = new PdfPCell(qrCodeImage);
        cQR.setBorder(Rectangle.NO_BORDER);
        cQR.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cQR.setHorizontalAlignment(Element.ALIGN_CENTER);
        tQR.addCell(cQR);

        PdfPCell cText = new PdfPCell(new Phrase("Representación gráfica del documento electrónico, este puede ser consultado en http://www.sunat.gob.pe/", fontH7));
        cText.setBorder(Rectangle.NO_BORDER);
        cText.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cText.setHorizontalAlignment(Element.ALIGN_CENTER);
        tQR.addCell(cText);

        document.add(tHeader);
        document.add(tDatosDestino);
        document.add(tDetalle);
        document.add(tDatosFirmas);
        document.add(tQR);
        
        document.close();
	}
	
	private static BitMatrix generateQRCode(String content, int size) {


        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
			return qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
        
        return null;
    }

    private static Image createQRCodeImage(BitMatrix bitMatrix, int size) {
        try {
            BufferedImage qrCodeBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeBufferedImage, "png", baos);
            return Image.getInstance(baos.toByteArray());
        } catch (IOException | BadElementException e) {
            e.printStackTrace();
        }
        return null;
    }
}