package com.infy.notify;

import com.infy.amqp.producer.RabbitMQMessageProducer;
import com.infy.notify.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = { "com.infy.amqp", "com.infy.notify" })
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotifyApp {
    public static void main(String[] args) {
        SpringApplication.run(NotifyApp.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
                                        NotificationConfig notificationConfig){
        return args -> {
            producer.publish(new Person("Vitaly", 32),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
        };
    }

    record Person(String name, int age){}*/
}
