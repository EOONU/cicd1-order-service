
package ie.atu.cicd1orderservice.controller;

import ie.atu.cicd1orderservice.model.PurchaseOrder;
import ie.atu.cicd1orderservice.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return purchaseOrderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder create(@RequestBody PurchaseOrder order) {
        return purchaseOrderService.create(order);
    }

    // Temporary integration test
    @GetMapping("/test-catalog/{productId}")
    public String testCatalogConnection(
            @PathVariable Long productId) {

        return purchaseOrderService
                .testCatalogConnection(productId);
    }
}
