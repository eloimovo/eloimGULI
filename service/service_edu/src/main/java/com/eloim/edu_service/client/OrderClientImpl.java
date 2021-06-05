package com.eloim.edu_service.client;

import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public Boolean isBuyCourse(String memberId, String courseId) {
        return false;
    }
}
