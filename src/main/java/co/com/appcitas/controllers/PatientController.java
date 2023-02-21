package co.com.appcitas.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appcitas.model.entities.Patient;
import co.com.appcitas.model.services.IPatientService;
import co.com.appcitas.model.services.PatientPDFExporter;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/list")
	public List<Patient> findAll(){
		return patientService.findAll();
	}
	
	@GetMapping("/show/{id}")
	public Patient findById(@PathVariable Integer id) {
		return patientService.findById(id);
	}
	
	@PostMapping("/save")
	public Patient save(@RequestBody Patient patient) {
		return patientService.save(patient);
	}
	
	@PutMapping("/edit/{id}")
	public Patient editar(@RequestBody Patient patient, @PathVariable Integer id) {
		Patient foundPatient = patientService.findById(id);
		
		foundPatient.setIdCard(patient.getIdCard());
		foundPatient.setFirstName(patient.getFirstName());
		foundPatient.setLastName(patient.getLastName());
		foundPatient.setBirthDate(patient.getBirthDate());
		foundPatient.setEmail(patient.getEmail());
		
		return patientService.save(foundPatient);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		patientService.delete(id);
	}
	
	@GetMapping("/pdf/{id}")
	public void exportPatientPdf(@PathVariable Integer id, HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");//tipo de medio de respuesta
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");//lo usaremos para el nombre del archivo
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";//nombre archivo pdf
        response.setHeader(headerKey, headerValue);
        
        Patient foundPatient = patientService.findById(id);//obtenemos el paciente por id.
        PatientPDFExporter exportPatientPdf = new PatientPDFExporter(foundPatient);//instancia de un objeto de la clase PatientPDFExporter, envio del objeto paciente por parametro para q lo procese
        exportPatientPdf.export(response);
	         
	}
}
