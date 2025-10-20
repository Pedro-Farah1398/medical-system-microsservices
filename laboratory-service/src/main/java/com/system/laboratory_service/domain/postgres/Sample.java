package com.system.laboratory_service.domain.postgres;

import com.system.laboratory_service.domain.enums.SampleStatus;
import com.system.laboratory_service.domain.enums.SampleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Enumerated(EnumType.STRING)
    private SampleType sampleType;

    @Column
    private Date collectedAt;

    @Enumerated(EnumType.STRING)
    private SampleStatus sampleStatus;

    @Column
    private String storageLocation;

    @Column
    private String barCode;


}
