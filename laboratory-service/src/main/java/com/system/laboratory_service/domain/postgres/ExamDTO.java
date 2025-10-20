package com.system.laboratory_service.domain.postgres;

import com.system.laboratory_service.domain.enums.ExamPriority;
import com.system.laboratory_service.domain.enums.ExamType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExamDTO {

    private UUID patientId;
    private UUID physicianId;
    private ExamType testExamType;
    private ExamPriority testPriority;
}
