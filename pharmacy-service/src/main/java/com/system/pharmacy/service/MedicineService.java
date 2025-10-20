package com.system.pharmacy.service;

import com.system.pharmacy.entity.medicine.Medicine;
import com.system.pharmacy.repository.MedicineRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public List<Medicine> findAll(Specification<Medicine> specification) {
        return medicineRepository.findAll(specification);
    }

    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

}
