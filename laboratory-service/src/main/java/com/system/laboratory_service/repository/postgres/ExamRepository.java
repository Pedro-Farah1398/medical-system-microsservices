package com.system.laboratory_service.repository.postgres;

import com.system.laboratory_service.domain.postgres.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {


}
