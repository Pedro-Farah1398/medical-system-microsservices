package com.system.pharmacy.entity.prescription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionInput {
    private String doctorName;
    private String patientName;
    private Set<String> medicineIDs;
    private Date date;
}
