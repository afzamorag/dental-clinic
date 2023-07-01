package com.digitalhouse.dentalclinic.service;

import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Patient savePatient(Patient row){
        return repository.save(row);
    }

    public Optional<Patient> findById(Long id){
        return repository.findById(id);
    }

    public Optional<Patient> findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Optional<Patient> findByNameAndLastName(String name, String lastName){
        return repository.findByNameAndLastName(name, lastName);
    }

    public Optional<Patient> findByIdNumber(String idNumber){
        return repository.findByIdNumber(idNumber);
    }

    public Optional<Patient> findByName(String name){
        return repository.findByName(name);
    }

    public Optional<Patient> findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }

    public void update(Patient row){
        repository.save(row);
    }

    public void delete(Long id){
        repository.deleteById(id);;
    }

    public Optional<List<Patient>> findAll(){
        return Optional.of(repository.findAll());
    }

}
