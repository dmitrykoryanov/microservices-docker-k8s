package com.infy.notify.service;

import com.infy.clients.dto.NotificationRequest;
import com.infy.notify.model.Notification;
import com.infy.notify.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationRequest notificationRequest){

        Notification notification = Notification.builder()
                .message(notificationRequest.message())
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sentAt(LocalDateTime.now())
                .sender("Dmitry Koryanov")
                .build();
        notificationRepository.saveAndFlush(notification);
        log.info("Notification with Id {} has been saved", notification.getNotificationId());
    }
}
