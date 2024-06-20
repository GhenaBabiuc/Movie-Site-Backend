package com.example.service.user.impl;

import com.example.model.user.SupportRequest;
import com.example.repository.user.SupportRequestRepository;
import com.example.service.user.SupportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportServiceImpl implements SupportService {

    @Resource
    private SupportRequestRepository supportRequestRepository;

    @Override
    public SupportRequest createSupportRequest(SupportRequest supportRequest) {
        return supportRequestRepository.save(supportRequest);
    }

    @Override
    public List<SupportRequest> getAllSupportRequests() {
        return supportRequestRepository.findAllByIsResolvedFalse();
    }

    @Override
    public SupportRequest markAsResolved(Long id) {
        SupportRequest supportRequest = supportRequestRepository.findById(id).orElseThrow();
        supportRequest.setResolved(true);
        return supportRequestRepository.save(supportRequest);
    }
}
