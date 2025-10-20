package com.system.laboratory_service.domain.postgres;

import com.system.laboratory_service.domain.enums.ReportFormat;
import com.system.laboratory_service.domain.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "lab_reports")
public class LabReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private Date createdAt;

    private String reportPath;

    private String summary;

    private UUID signedBy;

    private ReportFormat reportFormat;

    private ReportStatus reportStatus;
}
