package com.system.pharmacy.resolver;

import com.system.pharmacy.entity.prescription.Prescription;
import com.system.pharmacy.entity.prescription.PrescriptionInput;
import com.system.pharmacy.service.PrescriptionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @QueryMapping
    public List<Prescription> prescriptions() {
        return prescriptionService.findAll();
    }

    @MutationMapping
    public Prescription newPrescription(@Argument PrescriptionInput prescriptionInput) {
            return prescriptionService.handleNewPrescriptionCreation(prescriptionInput);
    }
}
