package com.digitalhouse.dentalclinic.controller;

import com.digitalhouse.dentalclinic.entity.Dentist;
import com.digitalhouse.dentalclinic.entity.Patient;
import com.digitalhouse.dentalclinic.entity.Turn;
import com.digitalhouse.dentalclinic.service.DentistService;
import com.digitalhouse.dentalclinic.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/turn")
public class TurnController {

    @Autowired
    private TurnService service;

    @PostMapping
    public ResponseEntity<Turn> saveTurn(@RequestBody Turn row){
        Turn t = service.save(row);
        System.out.println(t.toString());
        //return ResponseEntity.ok(service.save(row));
        return ResponseEntity.ok(t);
    }

    @GetMapping
    public ResponseEntity<List<Turn>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/turnbypatient/{idNumber}")
    public ResponseEntity<List<Turn>> findTurnByPatient(@PathVariable String idNumber){
        return ResponseEntity.ok(service.findTurnByPatient(idNumber));
    }

    @GetMapping("/turnbydentist/{registrationNumber}")
    public ResponseEntity<List<Turn>> findTurnByDoctor(@PathVariable String registrationNumber){
        return ResponseEntity.ok(service.findTurnByDentist(registrationNumber));
    }
}
