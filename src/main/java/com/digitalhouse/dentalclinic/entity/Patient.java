package com.digitalhouse.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
@Getter
@Setter

public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String idNumber;
    @Column
    private LocalDate dateRegister;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_Address_id", referencedColumnName = "id")
    private HomeAddress homeAddress;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turn> turns= new ArrayList<>();

    public Patient() {
    }

    public Patient(String name, String lastName, String idNumber, LocalDate dateRegister, HomeAddress homeAddress, String email) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.dateRegister = dateRegister;
        this.homeAddress = homeAddress;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", dateRegister=" + dateRegister +
                ", homeAddress=" + homeAddress +
                ", email='" + email + '\'' +
                '}';
    }
}
