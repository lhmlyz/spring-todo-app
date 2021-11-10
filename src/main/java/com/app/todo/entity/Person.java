package com.app.todo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name", length = 512)
    private String name;

    @Column(name="last_name", length = 512)
    private String surname;

    @Column(name="email", length = 512)
    private String email;

}
