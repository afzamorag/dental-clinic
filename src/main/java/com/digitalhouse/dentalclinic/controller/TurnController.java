package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.entity.Turn;
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
            if (this.validatePatientAndDentist(row)) {

            } else {

            }
        } catch (Exception exception) {

        }


        return ResponseEntity.ok(service.save(row));
    }

    @GetMapping
    public ResponseEntity<List<Turn>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/turnbypatient/{idNumber}")
    public ResponseEntity<List<Turn>> findTurnByPatient(@PathVariable String idNumber) {
        return ResponseEntity.ok(service.findTurnByPatient(idNumber));
    }

    @GetMapping("/turnbydentist/{registrationNumber}")
    public ResponseEntity<List<Turn>> findTurnByDoctor(@PathVariable String registrationNumber) {
        return ResponseEntity.ok(service.findTurnByDentist(registrationNumber));
    }

    public boolean validatePatientAndDentist(Turn row) {
        Optional<Patient> patientOptional = patientService.findById(row.getPatient().getId());
        Optional<Dentist> dentistOptional = dentistService.findById(row.getDentist().getId());
        return patientOptional.isPresent() && dentistOptional.isPresent();
    }
}
