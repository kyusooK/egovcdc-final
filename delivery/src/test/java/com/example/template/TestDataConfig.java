package com.example.template;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import egovk.domain.Delivery;
import egovk.domain.DeliveryRepository;

@TestConfiguration
public class TestDataConfig {

    @Bean
    public CommandLineRunner initData(DeliveryRepository repository) {
        return args -> {
            Delivery delivery = new Delivery();
            delivery.setDeliveryId("O12");
            delivery.setOrderId("O12");
            delivery.setProductId("P12");
            delivery.setProductName("TV");
            delivery.setQty(10);
            repository.save(delivery);
        };
    }
}