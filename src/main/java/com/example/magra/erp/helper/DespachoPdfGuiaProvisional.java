package com.example.magra.erp.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.despacho.Formulario;
import com.example.magra.erp.models.entity.despacho.FormularioDetalle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DespachoPdfGuiaProvisional {
	public static void generarGuiaProvisional(Formulario formulario) throws FileNotFoundException, DocumentException {
		Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(VariablesGlobales.GUIAS_PROVICIONAL + "/guia_provicional.pdf"));
        document.open();
        
        Paragraph cliente = new Paragraph(formulario.getOrdenTrabajo().getOrdenVenta().getCliente().getRazonSocial(), new Font(Font.FontFamily.COURIER, 12));
        Paragraph direccion = new Paragraph(formulario.getDestino(), new Font(Font.FontFamily.COURIER, 12));
        Paragraph ruc = new Paragraph(formulario.getOrdenTrabajo().getOrdenVenta().getCliente().getNroDocumentoIdentidad(), new Font(Font.FontFamily.COURIER, 12));
        Paragraph espacio = new Paragraph("      ", new Font(Font.FontFamily.COURIER, 12));
        
        Date fechaDate = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formato.format(fechaDate);
        
        Paragraph fecha = new Paragraph(fechaString, new Font(Font.FontFamily.COURIER, 12));
        
        document.add(cliente);
        document.add(direccion);
        document.add(ruc);
        document.add(espacio);
        document.add(espacio);
        document.add(espacio);
        document.add(espacio);
        document.add(fecha);
        document.add(espacio);
        document.add(espacio);
        document.add(espacio);
        
        PdfPTable tHeader = new PdfPTable(new float[]{10,1,5});
        tHeader.setWidthPercentage(100);
        tHeader.setSpacingBefore(5f);
        tHeader.setSpacingAfter(5f);
        
        for(FormularioDetalle detalle: formulario.getDetalle()) {
            
            PdfPCell cProducto= new PdfPCell(new Paragraph(detalle.getOrdenTrabajoDetalle().getOrdenVentaDetalle().getProducto().getNombre(), new Font(Font.FontFamily.COURIER, 12)));
            cProducto.setBorder(Rectangle.NO_BORDER);
            tHeader.addCell(cProducto);
            
            PdfPCell cCantidad= new PdfPCell(new Paragraph(detalle.getCantidad().toString(), new Font(Font.FontFamily.COURIER, 12)));
            cCantidad.setBorder(Rectangle.NO_BORDER);
            tHeader.addCell(cCantidad);
            
            PdfPCell cVacio= new PdfPCell(new Paragraph(" ", new Font(Font.FontFamily.COURIER, 12)));
            cVacio.setBorder(Rectangle.NO_BORDER);
            tHeader.addCell(cVacio);
        	
        }
        
        document.add(tHeader);
        
        document.close();
	}
}