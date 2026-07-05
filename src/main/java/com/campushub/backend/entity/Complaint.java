package com.campushub.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentUsername; // stored so the student can edit their own later — never sent to staff
    private String type;            // "COMPLAINT", "SUGGESTION", "BUG"

    @Column(length = 2000)
    private String message;

    private String createdDate;

    @Column(length = 2000)
    private String replyMessage;

    private String replyDate;
    private String status; // "OPEN" or "REPLIED"

    public Complaint() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    public String getReplyMessage() { return replyMessage; }
    public void setReplyMessage(String replyMessage) { this.replyMessage = replyMessage; }
    public String getReplyDate() { return replyDate; }
    public void setReplyDate(String replyDate) { this.replyDate = replyDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}