package com.digitalhouse.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime dateTurn;

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
