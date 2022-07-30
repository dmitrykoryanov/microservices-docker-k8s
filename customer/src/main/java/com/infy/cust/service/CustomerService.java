package com.infy.cust.service;

import com.infy.amqp.producer.RabbitMQMessageProducer;
import com.infy.clients.dto.FraudCheckResponse;
import com.infy.clients.dto.NotificationRequest;
import com.infy.clients.fraud.FraudClient;
import com.infy.clients.notify.NotifyClient;
import com.infy.cust.dto.CustomerRegistrationRequest;
import com.infy.cust.model.Customer;
import com.infy.cust.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    //private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotifyClient notifyClient;
    private final RabbitMQMessageProducer messageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

/*        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://fraud/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Message for customer " + customer.getFirstname() + " " + customer.getLastname());

/*        notifyClient.saveNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        "Message for customer " + customer.getFirstname() + " " + customer.getLastname())
        );*/

        messageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
