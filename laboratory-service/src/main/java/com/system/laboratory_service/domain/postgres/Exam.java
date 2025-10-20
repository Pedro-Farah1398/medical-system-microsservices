package com.system.laboratory_service.domain.postgres;

import com.system.laboratory_service.domain.enums.ExamPriority;
import com.system.laboratory_service.domain.enums.ExamStatus;
import com.system.laboratory_service.domain.enums.ExamType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID patientId;

    @Column(nullable = false)
    private UUID physicianId;

    @Enumerated(EnumType.STRING)
    private ExamType testExamType;

    @Enumerated(EnumType.STRING)
    private ExamStatus testStatus;

    @Column
    private Date requestedAt;

    @Column
    private Date completedAt;

    @Enumerated(EnumType.STRING)
    private ExamPriority testPriority;

    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL)
    private Sample sample;

    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL)
    private LabReport report;

    private String resultId;






}
