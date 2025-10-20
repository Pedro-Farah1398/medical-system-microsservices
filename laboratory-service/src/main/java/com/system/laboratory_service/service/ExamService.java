package com.system.laboratory_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.events.laboratory.ExamRequestedEvent;
import com.system.laboratory_service.domain.enums.ExamPriority;
import com.system.laboratory_service.domain.enums.ExamStatus;
import com.system.laboratory_service.domain.enums.ExamType;
import com.system.laboratory_service.domain.postgres.Exam;
import com.system.laboratory_service.domain.postgres.ExamDTO;
import com.system.laboratory_service.domain.postgres.OutboxEvent;
import com.system.laboratory_service.repository.postgres.ExamRepository;
import com.system.laboratory_service.repository.postgres.OutboxEventRepository;
import com.system.laboratory_service.util.ExamMapper;
import com.system.laboratory_service.util.OutboxMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final OutboxEventRepository outboxEventRepository;

    public ExamService(ExamRepository examRepository, OutboxEventRepository outboxEventRepository) {
        this.examRepository = examRepository;
        this.outboxEventRepository = outboxEventRepository;
    }

    public List<Exam> getExamByStatus(ExamStatus examStatus) {
        return examRepository.findAll().
                stream().
                filter(exam -> exam.getTestStatus() == examStatus).
                collect(Collectors.toList());
    }

    public List<Exam> getExamsByType(ExamType examType) {
        return examRepository.findAll().
                stream().
                filter(exam -> exam.getTestExamType() == examType).
                collect(Collectors.toList());
    }

    public List<Exam> getExamsByPriority(ExamPriority examPriority) {
        return examRepository.findAll().
                stream().
                filter(exam -> exam.getTestPriority() == examPriority).
                collect(Collectors.toList());
    }

    public Optional<Exam> getExamById(UUID examId) {
        return examRepository.findById(examId);
    }

    public List<Exam> getExamsByPatientId(UUID patientId) {
        return examRepository.findAll().
                stream().
                filter(exam -> exam.getPatientId() == patientId).
                collect(Collectors.toList());
    }

    @Transactional
    public Exam createExam(ExamDTO examDTO) throws JsonProcessingException {
        Exam exam = ExamMapper.createExam(examDTO);
        examRepository.save(exam);

        ExamRequestedEvent event = getExamRequestedEvent(exam);
        OutboxEvent outboxEvent = OutboxMapper.createOutboxEvent(exam, event);
        outboxEventRepository.save(outboxEvent);

        return exam;
    }

    private static ExamRequestedEvent getExamRequestedEvent(Exam exam) {
        return new ExamRequestedEvent(
                exam.getId(),
                exam.getPatientId(),
                exam.getPhysicianId(),
                exam.getTestExamType().name(),
                exam.getTestPriority().name(),
                exam.getRequestedAt()
        );
    }

}
