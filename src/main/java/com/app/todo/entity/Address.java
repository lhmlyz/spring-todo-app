package com.app.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String country;
    private String region;
    private String city;
    private String info;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
