package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.exception.BadRequestException;
import com.digitalhouse.dentalclinic.exception.ResourceNotFoundException;
import com.digitalhouse.dentalclinic.service.PatientService;
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

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() throws Exception {
        try{
            Optional<List<Patient>> list = service.findAll();
            if(!list.isEmpty()){
                return ResponseEntity.ok(list.get());
            }else{
                throw new ResourceNotFoundException("No existen pacientes registrados en el sistema");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient row) throws Exception {
        Optional<Patient> selected = service.findByEmail(row.getEmail());
        try{
            if(!selected.isPresent()){
                return ResponseEntity.ok(service.savePatient(row));
            }else{
                throw new BadRequestException("No se permite el registro de más de un paciente con el mismo correo electrónico");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) throws Exception {
        try{
            Optional<Patient> patientLocal = service.findById(id);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con el id indicado");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> findByEmail(@PathVariable String email) throws Exception {
        try{
            Optional<Patient> patientLocal = service.findByEmail(email);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con el email indicado");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Patient> findByName(@PathVariable String name) throws Exception{
        try{
            Optional<Patient> patientLocal = service.findByName(name);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con el nombre indicado");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Patient> findByLastName(@PathVariable String lastName) throws Exception{
        try{
            Optional<Patient> patientLocal = service.findByLastName(lastName);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con el apellido indicado");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/{name}/lastname/{lastName}")
    public ResponseEntity<Patient> findByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName) throws Exception{
        try{
            Optional<Patient> patientLocal = service.findByNameAndLastName(name, lastName);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con el nombre y apellido indicado");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<Patient> findByIdNumber(@PathVariable String idNumber) throws Exception{
        try{
            Optional<Patient> patientLocal = service.findByIdNumber(idNumber);
            if(patientLocal.isPresent()){
                return ResponseEntity.ok(patientLocal.get());
            }else{
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/update")
    public void update(@RequestBody Patient row) throws Exception{
        try{
            Optional<Patient> selected = service.findById(row.getId());
            if(selected.isPresent()){
                service.update(row);
            }else{
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws Exception{
        try{
            Optional<Patient> selected = service.findById(id);
            if(selected.isPresent()){
                service.delete(id);
            }else{
                throw new ResourceNotFoundException("Paciente no existe con la identificación indicada");
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

}
