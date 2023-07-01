package com.digitalhouse.dentalclinic.repository;

import com.digitalhouse.dentalclinic.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long>{

    @Query("SELECT t FROM Turn t WHERE t.dentist.registrationNumber = :registrationNumber")
    List<Turn> findTurnByDentist(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT t FROM Turn t WHERE t.patient.idNumber = :idNumber")
    List<Turn> findTurnByPatient(@Param("idNumber") String idNumber);

}
