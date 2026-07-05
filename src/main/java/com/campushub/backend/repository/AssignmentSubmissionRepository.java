package com.campushub.backend.repository;

import com.campushub.backend.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    List<AssignmentSubmission> findByStudentUsername(String studentUsername);
    List<AssignmentSubmission> findByAssignment_CourseAndAssignment_Batch(String course, String batch);
    void deleteByAssignment_Id(Long assignmentId);
}
