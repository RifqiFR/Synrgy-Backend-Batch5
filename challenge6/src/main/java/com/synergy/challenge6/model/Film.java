package com.synergy.challenge6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "films")
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String code;
    private String name;
    private boolean sedangTayang;
}
