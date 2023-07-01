package com.digitalhouse.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "turns")
@Getter
@Setter

public class Turn{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Patient patient;

    @JoinColumn(name = "dentist_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dentist dentist;

    @Column
    private LocalDate dateTurn;

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", patient=" + patient.toString() +
                ", dentist=" + dentist.toString() +
                ", dateTurn=" + dateTurn +
                '}';
    }
}
