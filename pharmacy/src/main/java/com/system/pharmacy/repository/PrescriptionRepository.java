package com.system.pharmacy.repository;

import com.system.pharmacy.entity.prescription.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

}
