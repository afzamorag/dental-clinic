package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.exception.BadRequestException;
import com.digitalhouse.dentalclinic.exception.ResourceNotFoundException;
import com.digitalhouse.dentalclinic.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    private static final Logger logger= Logger.getLogger(PatientService.class);

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() throws Exception {
        logger.info("Call method findAll() of Class Patient, params: {}");
        try{
            Optional<List<Patient>> list = service.findAll();
            if(list.get().size() > 0){
                return ResponseEntity.ok(list.get());
            }else{
                logger.warn("Call method findAll() of Class Patient ResourceNotFoundException");
                throw new ResourceNotFoundException("No existen pacientes registrados en el sistema");
            }
        }catch(ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient row) throws Exception {
        logger.info("Call method savePatient() of Class Patient, params: {" + row.toString() + "}");
        try{
            Optional<Patient> selected = service.findByEmail(row.getEmail());
            if(!selected.isPresent()){
                return ResponseEntity.ok(service.savePatient(row));
            }else{
                logger.error("Call method findAll() of Class Patient BadRequestException, params{" + row.toString() + "}");
                throw new BadRequestException("No se permite el registro de más de un paciente con el mismo correo electrónico");
            }
        }catch (BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        catch(Exception ex){
            logger.error("Call method findAll() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) throws Exception {
        logger.info("Call method findById() of Class Patient, params: {" + id + "}");
        try{
            Optional<Patient> patientLocal = service.findById(id);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findById() of Class Patient BadRequestException, params{" + id + "}");
                throw new ResourceNotFoundException("Paciente no existe con el id indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }
        catch(Exception ex){
            logger.error("Call method findById() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> findByEmail(@PathVariable String email) throws Exception {
        logger.info("Call method findByEmail() of Class Patient, params: {" + email + "}");
        try{
            Optional<Patient> patientLocal = service.findByEmail(email);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findByEmail() of Class Patient BadRequestException, params{" + email + "}");
                throw new ResourceNotFoundException("Paciente no existe con el email indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByEmail() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Patient> findByName(@PathVariable String name) throws Exception{
        logger.info("Call method findByName() of Class Patient, params: {" + name + "}");
        try{
            Optional<Patient> patientLocal = service.findByName(name);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findByName() of Class Patient BadRequestException, params{" + name + "}");
                throw new ResourceNotFoundException("Paciente no existe con el nombre indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByName() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Patient> findByLastName(@PathVariable String lastName) throws Exception{
        logger.info("Call method findByLastName() of Class Patient, params: {" + lastName + "}");
        try{
            Optional<Patient> patientLocal = service.findByLastName(lastName);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findByLastName() of Class Patient BadRequestException, params{" + lastName + "}");
                throw new ResourceNotFoundException("Paciente no existe con el apellido indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByLastName() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/{name}/lastname/{lastName}")
    public ResponseEntity<Patient> findByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName) throws Exception{
        logger.info("Call method findByNameAndLastName() of Class Patient, params: {" + name + ", " + lastName + "}");
        try{
            Optional<Patient> patientLocal = service.findByNameAndLastName(name, lastName);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findByNameAndLastName() of Class Patient BadRequestException, params{" + name + ", " + lastName + "}");
                throw new ResourceNotFoundException("Paciente no existe con el nombre y apellido indicado");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByNameAndLastName() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<Patient> findByIdNumber(@PathVariable String idNumber) throws Exception{
        logger.info("Call method findByIdNumber() of Class Patient, params: {" + idNumber + "}");
        try{
            Optional<Patient> patientLocal = service.findByIdNumber(idNumber);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                logger.error("Call method findByIdNumber() of Class Patient BadRequestException, params{" + idNumber + "}");
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch(Exception ex){
            logger.error("Call method findByIdNumber() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/update")
    public void update(@RequestBody Patient row) throws Exception{
        logger.info("Call method update() of Class Patient, params: {" + row.toString() + "}");
        try{
            Optional<Patient> selected = service.findById(row.getId());
            if(selected.isPresent()){
                logger.warn("Patient before update {" + selected.toString() + "} - Patient after update{" + row.toString() + "}");
                service.update(row);
            }else{
                logger.error("Call method update() of Class Patient BadRequestException, params{" + row.toString() + "}");
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch (Exception ex){
            logger.error("Call method update() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws Exception{
        logger.info("Call method delete() of Class Patient, params: {" + id + "}");
        try{
            Optional<Patient> selected = service.findById(id);
            if(selected.isPresent()){
                logger.warn("Patient to delete, params{id: " + id + "}");
                service.delete(id);
            }else{
                logger.error("Call method delete() of Class Patient BadRequestException, params{" + id + "}");
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch(ResourceNotFoundException ex){
            throw new Exception(ex.getMessage());
        }catch (Exception ex){
            logger.error("Call method delete() of Class Patient:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

}
