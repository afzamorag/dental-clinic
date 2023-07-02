package com.digitalhouse.dentalclinic.repository;

import com.digitalhouse.dentalclinic.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long>{

    @Query("SELECT t FROM Turn t WHERE t.dentist.registrationNumber = :registrationNumber")
    List<Turn> findTurnByDentist(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT t FROM Turn t WHERE t.patient.idNumber = :idNumber")
    List<Turn> findTurnByPatient(@Param("idNumber") String idNumber);

    @Query("SELECT t FROM Turn t WHERE t.patient.idNumber = :idNumber AND t.dateTurn = :dateTurn")
    List<Turn> findTurnByPatientAndDateTurn(@Param("idNumber") String idNumber, @Param("dateTurn")LocalDateTime dateTurn);

    @Query("SELECT t FROM Turn t WHERE t.dentist.registrationNumber = :registrationNumber AND t.dateTurn = :dateTurn")
    List<Turn> findTurnByDentistAndDateTurn(@Param("registrationNumber") String idNumber, @Param("dateTurn")LocalDateTime dateTurn);

}
