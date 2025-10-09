package com.system.pharmacy.resolver;

import com.system.pharmacy.entity.medicine.Medicine;
import com.system.pharmacy.entity.medicine.MedicineInput;
import com.system.pharmacy.filter.MedicineFilter;
import com.system.pharmacy.filter.MedicineSpecification;
import com.system.pharmacy.service.MedicineService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller()
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @QueryMapping
    public List<Medicine> medicines(@Argument MedicineFilter filter) {
        if (null == filter) {
            return medicineService.findAll();
        }

        return medicineService.findAll(MedicineSpecification.withFilter(filter));
    }

    @MutationMapping
    public Medicine newMedicine(@Argument MedicineInput medicine) {
        return medicineService.save(new Medicine(null,
                medicine.getName(),
                medicine.getManufacturer(), medicine.getDosage(),
                medicine.getActiveIngredient(), medicine.getType(),
                medicine.getAdministration(),
                medicine.getAmount()
        ));
    }
}
