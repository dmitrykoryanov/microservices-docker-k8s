package com.infy.cust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = "com.infy.clients")
@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "com.infy.amqp",
                "com.infy.cust"
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class CustomerApp {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }
}

