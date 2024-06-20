package com.example.service.user;

import com.example.model.user.SupportRequest;

import java.util.List;

public interface SupportService {
    SupportRequest createSupportRequest(SupportRequest supportRequest);

    List<SupportRequest> getAllSupportRequests();

    SupportRequest markAsResolved(Long id);
}
