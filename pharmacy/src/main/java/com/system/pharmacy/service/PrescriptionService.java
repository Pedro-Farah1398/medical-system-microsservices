package com.system.pharmacy.service;

import com.system.pharmacy.entity.medicine.Medicine;
import com.system.pharmacy.entity.prescription.Prescription;
import com.system.pharmacy.entity.prescription.PrescriptionInput;
import com.system.pharmacy.exceptions.MedicineNotFoundException;
import com.system.pharmacy.exceptions.MedicineSoldOutException;
import com.system.pharmacy.kafka.producer.MedicineEventProducer;
import com.system.pharmacy.kafka.producer.MedicineOutOfStockEvent;
import com.system.pharmacy.repository.MedicineRepository;
import com.system.pharmacy.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineEventProducer medicineEventProducer;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository, MedicineEventProducer medicineEventProducer) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.medicineEventProducer = medicineEventProducer;
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Transactional
    public Prescription handleNewPrescriptionCreation(PrescriptionInput prescriptionInput) {

        if (prescriptionInput == null) {
            return null;
        }

        Set<String> medicinesIds = prescriptionInput.getMedicineIDs();
        Set<Medicine> medicines = new HashSet<>();

        for (String medicineId : medicinesIds) {
            Medicine medicine = checkAndDecrementMedicineStock(Long.valueOf(medicineId));
            medicines.add(medicine);
        }
        return prescriptionRepository.save(new Prescription(null, prescriptionInput.getDoctorName(), prescriptionInput.getPatientName(),
                medicines, prescriptionInput.getDate()));
    }

    private Medicine checkAndDecrementMedicineStock(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine ID " + id + " not found"));

        if (medicine.getAmount() <= 0) {
            medicineEventProducer.sendMedicineOutOfStockEvent(transformMedicineToEventForm(medicine));
            throw new MedicineSoldOutException("Medicine " + medicine.getName() + " is out of stock");
        }

        medicine.setAmount(medicine.getAmount() - 1);
        return medicineRepository.save(medicine);
    }

    private MedicineOutOfStockEvent transformMedicineToEventForm(Medicine medicine) {
        return new MedicineOutOfStockEvent(medicine.getId(), medicine.getName(), Instant.now());
    }


}
