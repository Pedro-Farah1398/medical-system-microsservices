package com.system.laboratory_service.domain.postgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reagents")
public class Reagent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private Integer stockLevel;

    @Column
    private Integer threshold;

    @Column
    private String unit;

    @Column
    private Date lastUpdated;

}
