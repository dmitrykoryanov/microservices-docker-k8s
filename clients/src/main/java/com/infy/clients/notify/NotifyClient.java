package com.infy.clients.notify;

import com.infy.clients.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications", url = "${clients.notifications.url}")
public interface NotifyClient {

    @PostMapping(path = "api/v1/notify")
    void saveNotification(NotificationRequest notificationRequest);
}
