package com.digitalhouse.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dentists")
@Getter
@Setter

public class Dentist{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String registrationNumber;
    @Column
    private String name;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turn> turns = new ArrayList<>();

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
