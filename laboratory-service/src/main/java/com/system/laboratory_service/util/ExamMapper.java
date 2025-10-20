package com.system.laboratory_service.util;

import com.system.laboratory_service.domain.postgres.Exam;
import com.system.laboratory_service.domain.postgres.ExamDTO;

public class ExamMapper {

    public static Exam createExam(ExamDTO examDTO) {
        Exam exam = new Exam();

        exam.setTestExamType(examDTO.getTestExamType());
        exam.setTestPriority(examDTO.getTestPriority());
        exam.setPhysicianId(examDTO.getPhysicianId());
        exam.setPatientId(examDTO.getPatientId());

        return exam;
    }
}
