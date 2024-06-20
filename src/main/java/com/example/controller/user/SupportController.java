package com.example.controller.user;

import com.example.model.user.SupportRequest;
import com.example.service.user.SupportService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/support")
public class SupportController {

    @Resource
    private SupportService supportService;

    @PostMapping("/request")
    public ResponseEntity<SupportRequest> createSupportRequest(@RequestBody SupportRequest supportRequest) {
        supportRequest.setCreatedDate(LocalDateTime.now());
        supportRequest.setResolved(false);
        return ResponseEntity.ok(supportService.createSupportRequest(supportRequest));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<SupportRequest>> getAllSupportRequests() {
        return ResponseEntity.ok(supportService.getAllSupportRequests());
    }

    @PostMapping("/resolve/{id}")
    public ResponseEntity<SupportRequest> markAsResolved(@PathVariable Long id) {
        return ResponseEntity.ok(supportService.markAsResolved(id));
    }
}
