package co.com.appcitas.model.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.DocumentException;

import co.com.appcitas.model.entities.Patient;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

public class PatientPDFExporter {

	@SuppressWarnings("unused")
	private Patient patient;

	public PatientPDFExporter(Patient foundPatient) {
		this.patient = foundPatient;
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		String fullName = patient.getFirstName().concat(" " + patient.getLastName());
		
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate = LocalDate.parse(patient.getBirthDate().toString(), format);
		LocalDate nowDate = LocalDate.now();
		Period calculateDate = Period.between(birthDate, nowDate);
		
		document.open();
		
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		Font fontContent = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		fontTitle.setColor(Color.BLUE);
		fontContent.setSize(14);
		fontContent.setColor(Color.BLACK);

		Paragraph title = new Paragraph("PACIENTE:\n\n", fontTitle);
		Paragraph content = new Paragraph(	"Nombre: " + fullName + "\n"
											+ "Cedula: " + patient.getIdCard() + "\n"
											+ "Fecha Nacimiento: " + patient.getBirthDate()
											+ ",      Edad: " + calculateDate.getYears() + "\n"
											+ "Correo: " + patient.getEmail()
											, fontContent);
		
		title.setAlignment(Paragraph.ALIGN_CENTER);
		content.setAlignment(Paragraph.ALIGN_JUSTIFIED);

		document.add(title);
		document.add(content);
		document.close();
	}
}
