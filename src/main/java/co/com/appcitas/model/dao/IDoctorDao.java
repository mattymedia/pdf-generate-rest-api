package co.com.appcitas.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.appcitas.model.entities.Doctor;

public interface IDoctorDao extends JpaRepository<Doctor, Integer> {

}
