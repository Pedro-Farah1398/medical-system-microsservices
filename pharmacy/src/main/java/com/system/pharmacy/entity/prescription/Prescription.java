package com.system.pharmacy.entity.prescription;

import com.system.pharmacy.entity.medicine.Medicine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    String doctorName;

    @Column
    String patientName;

    @ManyToMany
    @JoinTable
    Set<Medicine> medicine;

    @Column
    Date date;


}
