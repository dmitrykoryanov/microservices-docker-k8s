package com.infy.notify.controller;

import com.infy.clients.dto.NotificationRequest;
import com.infy.notify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/notify")
public class NotificationController {

    private final NotificationService notificationService;

    @LoadBalanced
    @PostMapping
    public void saveNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("Notification request for customerId {} has come", notificationRequest.toCustomerId());
        notificationService.saveNotification(notificationRequest);
    }
}