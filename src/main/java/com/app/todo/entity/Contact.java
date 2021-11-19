package com.app.todo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    @SequenceGenerator(
            name = "contact_seq")
    private Long id;

    private String type;
    private String value;

    @ManyToOne
    @JsonIgnore
    private Student student;

}
