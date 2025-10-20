package com.system.pharmacy.repository;

import com.system.pharmacy.entity.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineRepository  extends JpaRepository<Medicine,Long>, JpaSpecificationExecutor<Medicine> {

}
