package com.nt.generator;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nt.entities.HomeEntity;

@Component
public class PDFGenerator {

	
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream customerPDFReport(List<HomeEntity> homeEntity) throws FileNotFoundException {
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {

		

			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();

			// Add text to pdf File

			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.PINK);
			Paragraph paragraph = new Paragraph("DataBase Data", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(3);
			
			
			// Add PDF Table Header ->
			Stream.of("id", "address", "cource", "name", "phone", "roll").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (HomeEntity ent : homeEntity) {
				PdfPCell idCell = new PdfPCell(new Phrase(ent.getId()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell NameCell = new PdfPCell(new Phrase(ent.getName()));
				NameCell.setPaddingLeft(4);
				NameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				NameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(NameCell);

				PdfPCell addressPCell = new PdfPCell(new Phrase(ent.getAddress()));

				addressPCell.setPadding(4);
				addressPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				addressPCell.setVerticalAlignment(Element.ALIGN_LEFT);
				table.addCell(addressPCell);

				PdfPCell coursePCell = new PdfPCell(new Phrase(ent.getCource()));

				coursePCell.setPadding(4);
				coursePCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				coursePCell.setVerticalAlignment(Element.ALIGN_LEFT);
				table.addCell(coursePCell);

				PdfPCell PhomeCell = new PdfPCell(new Phrase(ent.getPhone()));

				PhomeCell.setPadding(4);
				PhomeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PhomeCell.setVerticalAlignment(Element.ALIGN_LEFT);
				table.addCell(PhomeCell);

				PdfPCell RollCell = new PdfPCell(new Phrase(String.valueOf(ent.getRoll())));
				RollCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				RollCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				RollCell.setPaddingRight(4);
				table.addCell(RollCell);
			}
			document.add(table);

			

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}


}






