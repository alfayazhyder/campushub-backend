package com.campushub.backend.controller;

import com.campushub.backend.entity.Assignment;
import com.campushub.backend.entity.AssignmentSubmission;
import com.campushub.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(originPatterns = {"http://localhost:4200", "https://*.vercel.app"})
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    // Teacher/Admin view: every student's submission for a given cohort, across all assignments
    @GetMapping("/cohort")
    public List<AssignmentSubmission> getCohort(
            @RequestParam String course, @RequestParam String batch) {
        return assignmentService.getCohortSubmissions(course, batch);
    }

    // Student view: only their own submissions
    @GetMapping("/student/{username}")
    public List<AssignmentSubmission> getStudent(@PathVariable String username) {
        return assignmentService.getStudentSubmissions(username);
    }

    // Teacher creates a new assignment for a whole cohort — auto-fans out to every student
    @PostMapping
    public Assignment create(@RequestBody Assignment assignment) {
        return assignmentService.createAssignment(assignment);
    }

    // Teacher fills in ONE student's submission date + mark
    @PutMapping("/submission/{id}")
    public AssignmentSubmission updateSubmission(@PathVariable Long id, @RequestBody AssignmentSubmission updated) {
        return assignmentService.updateSubmission(id, updated);
    }

    // Teacher deletes an entire assignment (removes it for every student in that cohort)
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }
}
