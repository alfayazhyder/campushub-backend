package com.campushub.backend.service;

import com.campushub.backend.entity.Assignment;
import com.campushub.backend.entity.AssignmentSubmission;
import com.campushub.backend.entity.User;
import com.campushub.backend.repository.AssignmentRepository;
import com.campushub.backend.repository.AssignmentSubmissionRepository;
import com.campushub.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentSubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    // Teacher creates the assignment ONCE — a blank submission row is auto-created for every student in that cohort
    public Assignment createAssignment(Assignment assignment) {
        Assignment saved = assignmentRepository.save(assignment);

        List<User> students = userRepository.findByRoleAndCourseAndBatch(
                "STUDENT", assignment.getCourse(), assignment.getBatch());

        for (User student : students) {
            AssignmentSubmission sub = new AssignmentSubmission();
            sub.setAssignment(saved);
            sub.setStudentUsername(student.getUsername());
            sub.setStudentName(student.getName());
            sub.setSubmissionDate(null);
            sub.setMark(null);
            submissionRepository.save(sub);
        }
        return saved;
    }

    public List<AssignmentSubmission> getCohortSubmissions(String course, String batch) {
        return submissionRepository.findByAssignment_CourseAndAssignment_Batch(course, batch);
    }

    public List<AssignmentSubmission> getStudentSubmissions(String username) {
        return submissionRepository.findByStudentUsername(username);
    }

    // Teacher fills in one student's submission date + mark at a time
    public AssignmentSubmission updateSubmission(Long id, AssignmentSubmission updated) {
        AssignmentSubmission existing = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        existing.setSubmissionDate(updated.getSubmissionDate());
        existing.setMark(updated.getMark());
        return submissionRepository.save(existing);
    }

    public void deleteAssignment(Long assignmentId) {
        submissionRepository.deleteByAssignment_Id(assignmentId);
        assignmentRepository.deleteById(assignmentId);
    }
}