package co.com.appcitas.model.services;

import java.util.List;

import co.com.appcitas.model.entities.Patient;

public interface IPatientService {
	
	public List<Patient> findAll();
	
	public Patient findById(Integer id);
	
	public Patient save(Patient patient);
	
	public void delete(Integer id);
}
