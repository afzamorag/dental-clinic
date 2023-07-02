package com.digitalhouse.dentalclinic.service;

import com.digitalhouse.dentalclinic.entity.Turn;
import com.digitalhouse.dentalclinic.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    @Autowired
    private TurnRepository repository;

    public List<Turn> findAll(){
       return repository.findAll();
    }

    public Optional<Turn> findById(Long id){
        return repository.findById(id);
    }

    public Turn save(Turn row){
        return repository.save(row);
    }

    public void update(Turn row){
        repository.save(row);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Turn> findTurnByDentist(String registrationNumber){
        return repository.findTurnByDentist(registrationNumber);
    }

    public List<Turn> findTurnByPatient(String idNumber){

        return repository.findTurnByPatient(idNumber);
    }

    public List<Turn> findTurnByPatientAndDateTurn(String idNumber, LocalDateTime dateTurn){
        return repository.findTurnByPatientAndDateTurn(idNumber, dateTurn);
    }

    public List<Turn> findTurnByDentistAndDateTurn(String registrationNumber, LocalDateTime dateTurn){
        return repository.findTurnByDentistAndDateTurn(registrationNumber, dateTurn);
    }

}
