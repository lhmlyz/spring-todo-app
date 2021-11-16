package com.app.todo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(
            name = "person_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "first_name", length = 512)
    private String name;

    @Column(name = "last_name", length = 512)
    private String surname;

    @Column(name = "email", length = 512)
    private String email;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            optional = false)
    private Address address;

}
