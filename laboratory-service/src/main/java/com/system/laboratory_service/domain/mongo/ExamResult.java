package com.system.laboratory_service.domain.mongo;

import com.system.laboratory_service.domain.enums.ResultStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Document(collection = "exam_results")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID examId;

    private String analyte;

    private String value;

    private String unit;

    private String referenceRange;

    private ResultStatus resultStatus;

    private Date measuredAt;

    private UUID deviceId;


}
