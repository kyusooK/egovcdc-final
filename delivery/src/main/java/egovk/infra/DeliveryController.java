package egovk.infra;

import egovk.domain.*;
import egovk.service.*;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/deliveries")
public class DeliveryController {

    @Resource(name = "deliveryService")
    private DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public List<Delivery> getAllDeliveries() throws Exception {
        // Get all deliveries via DeliveryService
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/deliveries/{deliveryId}")
    public Optional<Delivery> getDeliveryById(@PathVariable String deliveryId)
        throws Exception {
        // Get a delivery by ID via DeliveryService
        return deliveryService.getDeliveryById(deliveryId);
    }

    @PostMapping("/deliveries")
    public Delivery createDelivery(@RequestBody Delivery delivery)
        throws Exception {
        // Create a new delivery via DeliveryService
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/deliveries/{deliveryId}")
    public Delivery updateDelivery(
        @PathVariable String deliveryId,
        @RequestBody Delivery delivery
    ) throws Exception {
        // Update an existing delivery via DeliveryService
        return deliveryService.updateDelivery(delivery);
    }

    @DeleteMapping("/deliveries/{deliveryId}")
    public void deleteDelivery(@PathVariable String deliveryId)
        throws Exception {
        // Delete a delivery via DeliveryService
        deliveryService.deleteDelivery(deliveryId);
    }
}
