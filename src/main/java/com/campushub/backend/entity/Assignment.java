package com.campushub.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String course;
    private String batch;
    private String subject;
    private String assignmentName;
    private String dateGiven;
    private String lastDateToSubmit;

    public Assignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getAssignmentName() { return assignmentName; }
    public void setAssignmentName(String assignmentName) { this.assignmentName = assignmentName; }
    public String getDateGiven() { return dateGiven; }
    public void setDateGiven(String dateGiven) { this.dateGiven = dateGiven; }
    public String getLastDateToSubmit() { return lastDateToSubmit; }
    public void setLastDateToSubmit(String lastDateToSubmit) { this.lastDateToSubmit = lastDateToSubmit; }
}
