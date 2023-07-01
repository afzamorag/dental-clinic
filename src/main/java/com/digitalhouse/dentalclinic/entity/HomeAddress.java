package com.digitalhouse.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "home_address")

public class HomeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String street;
    @Column
    private Integer streetNumber;
    @Column
    private String location;
    @Column
    private String city;

    public HomeAddress() {
    }

    public HomeAddress(String street, Integer streetNumber, String location, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.location = location;
        this.city = city;
    }
}
