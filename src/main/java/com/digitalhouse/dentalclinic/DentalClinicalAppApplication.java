package com.digitalhouse.dentalclinic;

import com.digitalhouse.dentalclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalClinicalAppApplication {

	@Autowired
	static PatientService service;

	public static void main(String[] args) {

		SpringApplication.run(DentalClinicalAppApplication.class, args);
		/*LocalDate turnDate = LocalDate.parse("2019-12-15");
		HomeAddress home = new HomeAddress("CALLE OSPINA", 75, "SAN CRISTOBAL", "BOGOTA");
		Patient patient = new Patient("JUAN FELIPE", "ZAMORA PEREZ", "10103458765", turnDate, home, "juanfe@gmail.com");

		patient = service.savePatient(patient);
		Long id = patient.getId();
		patient = new Patient();
		patient = service.findById(id).get();
		System.out.println(patient.toString());*/

	}

}
