package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService service;

    @GetMapping
    public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Dentist> saveDentist(@RequestBody Dentist row){
        return ResponseEntity.ok(service.saveDentist(row));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id){
        Optional<Dentist> DentistLocal = service.findById(id);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Dentist> findByName(@PathVariable String name){
        Optional<Dentist> DentistLocal = service.findByName(name);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Dentist> findByLastName(@PathVariable String lastName){
        Optional<Dentist> DentistLocal = service.findByLastName(lastName);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}/lastname/{lastName}")
    public ResponseEntity<Dentist> findByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName){
        Optional<Dentist> DentistLocal = service.findByNameAndLastName(name, lastName);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/registrationNumber/{registrationNumber}")
    public ResponseEntity<Dentist> findByIdNumber(@PathVariable String registrationNumber){
        Optional<Dentist> DentistLocal = service.findByRegistrationNumber(registrationNumber);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public void update(@RequestBody Dentist row){
        service.update(row);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    
}
