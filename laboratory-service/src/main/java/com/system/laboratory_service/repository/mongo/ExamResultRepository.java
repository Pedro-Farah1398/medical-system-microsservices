package com.system.laboratory_service.repository.mongo;

import com.system.laboratory_service.domain.mongo.ExamResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExamResultRepository extends MongoRepository<ExamResult, UUID> {
}
