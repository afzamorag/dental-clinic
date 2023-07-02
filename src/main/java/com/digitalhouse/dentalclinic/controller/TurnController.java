package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.entity.Turn;
import com.digitalhouse.dentalclinic.exception.BadRequestException;
import com.digitalhouse.dentalclinic.exception.ResourceNotFoundException;
import com.digitalhouse.dentalclinic.service.DentistService;
import com.digitalhouse.dentalclinic.service.PatientService;
import com.digitalhouse.dentalclinic.service.TurnService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turn")
public class TurnController {

    @Autowired
    private TurnService service;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentistService dentistService;

    private static final Logger logger = Logger.getLogger(Turn.class);

    @PostMapping
    public ResponseEntity<Turn> saveTurn(@RequestBody Turn row) throws Exception {
        logger.info("Call method saveTurn() of Class Turn, params: {" + row.toString() + "}");
        try {
            if (this.validateDentist(row) && this.validatePatient(row)){
                return ResponseEntity.ok(service.save(row));
            } else {
                logger.error("Call method saveTurn() of Class Turn BadRequestException, params{" + row.toString() + "}");
                throw new BadRequestException("No se existe disponibilidad para la fecha y hora, por simultaneidad del paciente o dentista");
            }
        } catch (BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        catch(Exception ex){
            logger.error("Call method saveTurn() of Class Turn:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Turn>> findAll() throws Exception{
        logger.info("Call method findAll() of Class Turn, params: {}");
        try{
            List<Turn> list = service.findAll();
            if(list.size() > 0){
                return ResponseEntity.ok(list);
            }else{
                logger.warn("Call method findAll() of Class Turn ResourceNotFoundException");
                throw new ResourceNotFoundException("No existe información de turnos registrada en el sistema");
            }
        }catch(ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/turnByPatient/{idNumber}")
    public ResponseEntity<List<Turn>> findTurnByPatient(@PathVariable String idNumber) throws Exception{
        logger.info("Call method findTurnByPatient() of Class Turn, params: {" + idNumber + "}");
        try{
            List<Turn> list  = service.findTurnByPatient(idNumber);
            if(list.size() > 0){
                return ResponseEntity.ok(list);
            }else{
                logger.warn("Call method findTurnByPatient() of Class Turn ResourceNotFoundException");
                throw new ResourceNotFoundException("No existe información de turnos registrada para el paciente en el sistema");
            }
        }catch(ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping("/turnByDentist/{registrationNumber}")
    public ResponseEntity<List<Turn>> findTurnByDentist(@PathVariable String registrationNumber) throws Exception{
        logger.info("Call method findTurnByDentist() of Class Turn, params: {" + registrationNumber + "}");
        try{
            List<Turn> list  = service.findTurnByDentist(registrationNumber);
            if(list.size() > 0){
                return ResponseEntity.ok(list);
            }else{
                logger.warn("Call method findTurnByDentist() of Class Turn ResourceNotFoundException");
                throw new ResourceNotFoundException("No existe información de turnos registrada para el dentista en el sistema");
            }
        }catch(ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public boolean validatePatient(Turn row) {
        Optional<Patient> patientOptional = patientService.findById(row.getPatient().getId());
        if (patientOptional.isPresent()) {
            List<Turn> list = service.findTurnByPatientAndDateTurn(row.getPatient().getIdNumber(), row.getDateTurn());
            if (list.size() > 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean validateDentist(Turn row) {
        Optional<Dentist> dentistOptional = dentistService.findById(row.getDentist().getId());
        if (dentistOptional.isPresent()) {
            List<Turn> list = service.findTurnByDentistAndDateTurn(row.getDentist().getRegistrationNumber(), row.getDateTurn());
            if (list.size() > 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
