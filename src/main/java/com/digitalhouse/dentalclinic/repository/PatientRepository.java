package com.digitalhouse.dentalclinic.repository;

import com.digitalhouse.dentalclinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByName(String name);
    Optional<Patient> findByLastName(String lastName);
    Optional<Patient> findByIdNumber(String idNumber);
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByNameAndLastName(String name, String lastName);

}
