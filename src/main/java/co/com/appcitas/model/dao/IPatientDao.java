package co.com.appcitas.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.appcitas.model.entities.Patient;

public interface IPatientDao extends JpaRepository<Patient, Integer> {

}
