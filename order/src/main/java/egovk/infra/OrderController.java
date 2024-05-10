package egovk.infra;

import egovk.domain.*;
import egovk.service.*;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
// @RequestMapping(value="/orders")
public class OrderController {

    @Value("${api.url.delivery}")
    private String apiUrl;

    @Resource(name = "orderService")
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/validateDelivery/{deliveryId}")
    public ResponseEntity<String> deliveryCheck(@PathVariable(value = "deliveryId") String deliveryId) {
    
        String deliveryUrl = apiUrl + "/deliveries/" + deliveryId;
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
    
        ResponseEntity<String> deliveryEntity = restTemplate.exchange(deliveryUrl, HttpMethod.GET, entity, String.class);
    
        return deliveryEntity;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() throws Exception {
        // Get all orders via OrderService
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<Order> getOrderById(@PathVariable String orderId)
        throws Exception {
        // Get a order by ID via OrderService
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) throws Exception {
        // Create a new order via OrderService
        return orderService.createOrder(order);
    }

    @PutMapping("/orders/{deliveryId}")
    public Order updateOrder(
        @PathVariable String orderId,
        @RequestBody Order order
    ) throws Exception {
        // Update an existing order via OrderService
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable String orderId) throws Exception {
        // Delete a order via OrderService
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(
        value = "orders",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order acceptOrder(
        @RequestBody AcceptOrderCommand acceptOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        return orderService.acceptOrder(acceptOrderCommand);
    }

    @RequestMapping(
        value = "/orders/{id}/rejectorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order rejectOrder(
        @PathVariable(value = "id") String orderId,
        @RequestBody RejectOrderCommand rejectOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        return orderService.rejectOrder(rejectOrderCommand);
    }
}
