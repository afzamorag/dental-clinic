package com.digitalhouse.dentalclinic.service;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    @Autowired
    private DentistRepository repository;

    public Dentist saveDentist(Dentist row){
        return repository.save(row);
    }

    public Optional<Dentist> findById(Long id){
        return repository.findById(id);
    }

    public Optional<Dentist> findByName(String name){
        return repository.findByName(name);
    }

    public Optional<Dentist> findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }

    public Optional<Dentist> findByNameAndLastName(String name, String lastName){
        return repository.findByNameAndLastName(name, lastName);
    }

    public Optional<Dentist> findByRegistrationNumber(String registrationNumber){
        return repository.findByRegistrationNumber(registrationNumber);
    }

    public void update(Dentist row){
        repository.save(row);
    }

    public void delete(Long id){
        repository.deleteById(id);;
    }

    public List<Dentist> findAll(){
        return repository.findAll();
    }
}

