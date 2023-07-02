package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.exception.BadRequestException;
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
    public ResponseEntity<List<Dentist>> findAll() throws Exception {
        logger.info("Call method findAll() of Class Dentist, params: {}");
        try{
            Optional<List<Dentist>> list = service.findAll();
            if(list.get().size() > 0){
                return ResponseEntity.ok(list.get());
            }else{
                logger.warn("Call method findAll() of Class Dentist ResourceNotFoundException");
                throw new ResourceNotFoundException("No existen odontologos registrados en el sistema");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findAll() of Class Dentist, Exception: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<Dentist> saveDentist(@RequestBody Dentist row) throws Exception{
        logger.info("Call method savePatient() of Class Dentist, params: {" + row.toString() + "}");
        try{
            Optional<Dentist> selected = service.findByRegistrationNumber(row.getRegistrationNumber());
            if(!selected.isPresent()){
                return ResponseEntity.ok(service.saveDentist(row));
            }else{
                logger.error("Call method findAll() of Class Dentist BadRequestException, params{" + row.toString() + "}");
                throw new BadRequestException("No se permite el registro de más de un odontologo con el mismo número de matrícula");
            }
        }catch (BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findAll() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id) throws Exception{
        logger.info("Call method findById() of Class Dentist, params: {" + id + "}");
        try{
            Optional<Dentist> DentistLocal = service.findById(id);
            if(DentistLocal.isPresent()){
                return ResponseEntity.ok(DentistLocal.get());
            }else{
                logger.error("Call method findById() of Class Dentist BadRequestException, params{" + id + "}");
                throw new ResourceNotFoundException("Odontologo no existe con el id indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findById() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Dentist> findByName(@PathVariable String name){
        logger.info("Call method findByName() of Class Dentist, params: {" + name + "}");
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
        logger.info("Call method findByLastName() of Class Dentist, params: {" + lastName + "}");
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
        logger.info("Call method findByNameAndLastName() of Class Dentist, params: {" + name + ", " + lastName + "}");
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
    public ResponseEntity<Dentist> findByRegistrationNumber(@PathVariable String registrationNumber){
        logger.info("Call method findByRegistrationNumber() of Class Dentist, params: {" + registrationNumber + "}");
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
        logger.info("Call method update() of Class Dentist, params: {" + row.toString() + "}");
        try{

        }catch(Exception ex){

        }

        service.update(row);
    }
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Call method delete() of Class Dentist, params: {" + id + "}");
        try{

        }catch(Exception ex){

        }

        service.delete(id);
    }
    
}
