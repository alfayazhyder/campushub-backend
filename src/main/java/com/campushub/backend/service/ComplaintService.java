package com.campushub.backend.service;

import com.campushub.backend.entity.Complaint;
import com.campushub.backend.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getByStudent(String username) {
        return complaintRepository.findByStudentUsername(username);
    }

    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

    // Used only when a student creates a brand new complaint
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    // Used only when a student edits their own existing complaint —
    // fetches the existing row first so a teacher's reply is never overwritten
    public Complaint editMessage(Long id, String type, String message) {
        Complaint existing = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        existing.setType(type);
        existing.setMessage(message);
        return complaintRepository.save(existing);
    }

    public Complaint reply(Long id, String replyMessage, String replyDate) {
        Complaint c = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        c.setReplyMessage(replyMessage);
        c.setReplyDate(replyDate);
        c.setStatus("REPLIED");
        return complaintRepository.save(c);
    }

    public void delete(Long id) {
        complaintRepository.deleteById(id);
    }
}