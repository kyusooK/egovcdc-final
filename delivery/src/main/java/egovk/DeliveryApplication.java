package egovk;

import egovk.config.kafka.KafkaProcessor;
import egovk.domain.Delivery;
import egovk.domain.DeliveryRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:egovframework/spring/context-*.xml")
@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class DeliveryApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(DeliveryApplication.class, args);

        DeliveryRepository deliveryRepository = applicationContext.getBean(DeliveryRepository.class);

        Delivery delivery = new Delivery();
        
        delivery.setDeliveryId("O12");
        delivery.setOrderId("O12");
        delivery.setProductId("P12");
        delivery.setProductName("TV");
        delivery.setQty(10);

        deliveryRepository.save(delivery);
    }
}
