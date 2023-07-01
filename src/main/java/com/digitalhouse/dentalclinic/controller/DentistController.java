package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.exception.ResourceNotFoundException;
import com.digitalhouse.dentalclinic.service.DentistService;
import com.digitalhouse.dentalclinic.service.PatientService;
import org.apache.log4j.Logger;
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

    private static final Logger logger= Logger.getLogger(DentistService.class);
    @GetMapping
    public ResponseEntity<List<Dentist>> findAll() throws Exception, ResourceNotFoundException {
        try{
            Optional<List<Dentist>> list = service.findAll();
            if(list.isPresent()){
                return ResponseEntity.ok(list.get());
            }else{
                logger.warn("Call method findAll() of Class Dentist ResourceNotFoundException");
                throw new ResourceNotFoundException("No existen odontologos registrados en el sistema");
            }
        }catch(Exception ex){
            logger.error("Call method findAll() of Class Patient, Exception: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Dentist> saveDentist(@RequestBody Dentist row){
        try{

        }catch(Exception ex){

        }
        return ResponseEntity.ok(service.saveDentist(row));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id){
        try{

        }catch(Exception ex){

        }
        Optional<Dentist> DentistLocal = service.findById(id);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Dentist> findByName(@PathVariable String name){
        try{

        }catch(Exception ex){

        }

        Optional<Dentist> DentistLocal = service.findByName(name);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Dentist> findByLastName(@PathVariable String lastName){
        try{

        }catch(Exception ex){

        }

        Optional<Dentist> DentistLocal = service.findByLastName(lastName);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{name}/lastname/{lastName}")
    public ResponseEntity<Dentist> findByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName){
        try{

        }catch(Exception ex){

        }

        Optional<Dentist> DentistLocal = service.findByNameAndLastName(name, lastName);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/registrationNumber/{registrationNumber}")
    public ResponseEntity<Dentist> findByIdNumber(@PathVariable String registrationNumber){
        try{

        }catch(Exception ex){

        }

        Optional<Dentist> DentistLocal = service.findByRegistrationNumber(registrationNumber);
        if(DentistLocal.isPresent()){
            return ResponseEntity.ok(DentistLocal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/update")
    public void update(@RequestBody Dentist row){
        try{

        }catch(Exception ex){

        }

        service.update(row);
    }
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        try{

        }catch(Exception ex){

        }

        service.delete(id);
    }
    
}
