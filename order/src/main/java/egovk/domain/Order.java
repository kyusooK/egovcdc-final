package egovk.domain;

import egovk.OrderApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    private String orderId;

    private String productId;

    private String productName;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public void acceptOrder(AcceptOrderCommand acceptOrderCommand) {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectOrder(RejectOrderCommand rejectOrderCommand) {
        //implement business logic here:

        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
