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
    public ResponseEntity<Dentist> findByName(@PathVariable String name) throws Exception{
        logger.info("Call method findByName() of Class Dentist, params: {" + name + "}");
        try{
            Optional<Dentist> DentistLocal = service.findByName(name);
            if(DentistLocal.isPresent()){
                return ResponseEntity.ok(DentistLocal.get());
            }else{
                logger.error("Call method findByName() of Class Dentist BadRequestException, params{" + name + "}");
                throw new ResourceNotFoundException("Odontologo no existe con el nombre indicado");
            }
        }catch(ResourceNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        catch(Exception ex){
            logger.error("Call method findByName() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Dentist> findByLastName(@PathVariable String lastName) throws Exception{
        logger.info("Call method findByLastName() of Class Dentist, params: {" + lastName + "}");
        try{
            Optional<Dentist> DentistLocal = service.findByLastName(lastName);
            if(DentistLocal.isPresent()){
                return ResponseEntity.ok(DentistLocal.get());
            }else{
                logger.error("Call method findByLastName() of Class Dentist BadRequestException, params{" + lastName + "}");
                throw new ResourceNotFoundException("Odontologo no existe con el apellido indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByLastName() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @GetMapping("/{name}/lastname/{lastName}")
    public ResponseEntity<Dentist> findByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName) throws Exception{
        logger.info("Call method findByNameAndLastName() of Class Dentist, params: {" + name + ", " + lastName + "}");
        try{
            Optional<Dentist> DentistLocal = service.findByNameAndLastName(name, lastName);
            if(DentistLocal.isPresent()){
                return ResponseEntity.ok(DentistLocal.get());
            }else{
                logger.error("Call method findByNameAndLastName() of Class Dentist BadRequestException, params{" + name + ", " + lastName + "}");
                throw new ResourceNotFoundException("Odontologo no existe con el nombre y apellido indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByNameAndLastName() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @GetMapping("/registrationNumber/{registrationNumber}")
    public ResponseEntity<Dentist> findByRegistrationNumber(@PathVariable String registrationNumber) throws Exception{
        logger.info("Call method findByRegistrationNumber() of Class Dentist, params: {" + registrationNumber + "}");
        try{
            Optional<Dentist> DentistLocal = service.findByRegistrationNumber(registrationNumber);
            if(DentistLocal.isPresent()){
                return ResponseEntity.ok(DentistLocal.get());
            }else{
                logger.error("Call method findByRegistrationNumber() of Class Dentist BadRequestException, params{" + registrationNumber + "}");
                throw new ResourceNotFoundException("Odontologo no existe con el número de matrícula indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByRegistrationNumber() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Dentist row) throws Exception{
        logger.info("Call method update() of Class Dentist, params: {" + row.toString() + "}");
        try{
            Optional<Dentist> selected = service.findById(row.getId());
            if(selected.isPresent()){
                logger.warn("Dentist before update {" + selected.toString() + "} - Patient after update{" + row.toString() + "}");
                service.update(row);
                return ResponseEntity.ok("Paciente actualizado correctamente" + row.toString());
            }else{
                logger.error("Call method update() of Class Dentist BadRequestException, params{" + row.toString() + "}");
                throw new ResourceNotFoundException("Odontologo no existe con la identificación indicada");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch (Exception ex){
            logger.error("Call method update() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception{
        logger.info("Call method delete() of Class Dentist, params: {" + id + "}");
        try{
            Optional<Dentist> selected = service.findById(id);
            if(selected.isPresent()){
                service.delete(id);
                return ResponseEntity.ok("Odontologo eliminado satisfactoriamente");
            }else{
                logger.error("Call method delete() of Class Dentist BadRequestException, params{" + id + "}");
                throw new ResourceNotFoundException("Odontologo no existe con la identificación indicada");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch (Exception ex){
            logger.error("Call method delete() of Class Dentist:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
}
