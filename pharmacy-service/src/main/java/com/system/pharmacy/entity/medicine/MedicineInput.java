package com.system.pharmacy.entity.medicine;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineInput {

    private String name;
    private String dosage;
    private String manufacturer;
    private Type type;
    private Administration administration;
    private String activeIngredient;
    private int amount;
    private String priority;
}
