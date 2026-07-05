package com.campushub.backend.dto;

public class ComplaintStaffView {
    private Long id;
    private String type;
    private String message;
    private String createdDate;
    private String replyMessage;
    private String replyDate;
    private String status;

    public ComplaintStaffView(Long id, String type, String message, String createdDate,
                              String replyMessage, String replyDate, String status) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.createdDate = createdDate;
        this.replyMessage = replyMessage;
        this.replyDate = replyDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getMessage() { return message; }
    public String getCreatedDate() { return createdDate; }
    public String getReplyMessage() { return replyMessage; }
    public String getReplyDate() { return replyDate; }
    public String getStatus() { return status; }
}