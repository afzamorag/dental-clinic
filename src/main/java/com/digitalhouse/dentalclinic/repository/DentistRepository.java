package com.digitalhouse.dentalclinic.repository;

import com.digitalhouse.dentalclinic.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    Optional<Dentist> findByName(String name);
    Optional<Dentist> findByLastName(String lastName);
    Optional<Dentist> findByRegistrationNumber(String idNumber);
    Optional<Dentist> findByNameAndLastName(String name, String lastName);

}
