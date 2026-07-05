package com.campushub.backend.controller;

import com.campushub.backend.dto.ComplaintStaffView;
import com.campushub.backend.entity.Complaint;
import com.campushub.backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(originPatterns = {"http://localhost:4200", "https://*.vercel.app"})
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // Student: see only their own complaints (needed so they can edit them)
    @GetMapping("/student/{username}")
    public List<Complaint> getStudentComplaints(@PathVariable String username) {
        return complaintService.getByStudent(username);
    }

    // Staff: see everything, but WITHOUT any student identity fields
    @GetMapping("/staff")
    public List<ComplaintStaffView> getAllForStaff() {
        return complaintService.getAll().stream()
                .map(c -> new ComplaintStaffView(
                        c.getId(), c.getType(), c.getMessage(), c.getCreatedDate(),
                        c.getReplyMessage(), c.getReplyDate(), c.getStatus()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Complaint create(@RequestBody Complaint complaint) {
        complaint.setStatus("OPEN");
        return complaintService.save(complaint);
    }

    // Student edits their own message — only type and message are touched,
    // so an existing teacher reply is preserved
    @PutMapping("/{id}")
    public Complaint edit(@PathVariable Long id, @RequestBody Complaint updated) {
        return complaintService.editMessage(id, updated.getType(), updated.getMessage());
    }

    // Staff replies
    @PutMapping("/{id}/reply")
    public Complaint reply(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return complaintService.reply(id, body.get("replyMessage"), body.get("replyDate"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        complaintService.delete(id);
    }
}