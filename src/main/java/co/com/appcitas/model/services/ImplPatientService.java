package co.com.appcitas.model.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.appcitas.model.dao.IPatientDao;
import co.com.appcitas.model.entities.Patient;

@Service
public class ImplPatientService implements IPatientService {

	@Autowired
	private IPatientDao patientDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Patient> findAll() {
		return patientDao.findAll();
	}

	@Override
	public Patient findById(Integer id) {
		return patientDao.findById(id).orElse(null);
	}

	@Override
	public Patient save(Patient patient) {
		return patientDao.save(patient);
	}

	@Override
	public void delete(Integer id) {
		patientDao.deleteById(id);
	}
}
