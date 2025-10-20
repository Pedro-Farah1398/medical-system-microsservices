package com.system.laboratory_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.laboratory_service.domain.enums.ExamPriority;
import com.system.laboratory_service.domain.enums.ExamStatus;
import com.system.laboratory_service.domain.enums.ExamType;
import com.system.laboratory_service.domain.postgres.Exam;
import com.system.laboratory_service.domain.postgres.ExamDTO;
import com.system.laboratory_service.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Exam>> getExamsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(examService.getExamByStatus(ExamStatus.valueOf(status)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Exam>> getExamsByType(@PathVariable String type) {
        return ResponseEntity.ok(examService.getExamsByType(ExamType.valueOf(type)));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Exam>> getExamsByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(examService.getExamsByPriority(ExamPriority.valueOf(priority)));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Exam>> getExamsByPatientId(@PathVariable UUID patientId) {
        return ResponseEntity.ok(examService.getExamsByPatientId(patientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamsById(@PathVariable UUID id) {
        return examService.getExamById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody ExamDTO examDTO) throws JsonProcessingException {
        Exam createdExam =  examService.createExam(examDTO);
        return ResponseEntity
                .created(URI.create("/exams/" + createdExam.getId()))
                .body(createdExam);
    }
}
