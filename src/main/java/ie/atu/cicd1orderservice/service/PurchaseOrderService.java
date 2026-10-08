
package ie.atu.cicd1orderservice.service;

import ie.atu.cicd1orderservice.client.CatalogClient;
import ie.atu.cicd1orderservice.client.dto.ProductResponse;
import ie.atu.cicd1orderservice.model.PurchaseOrder;
import ie.atu.cicd1orderservice.repository.PurchaseOrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository repository;
    private final CatalogClient catalogClient;

    public PurchaseOrderService(
            PurchaseOrderRepository repository,
            CatalogClient catalogClient) {

        this.repository = repository;
        this.catalogClient = catalogClient;
    }

    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }

    public PurchaseOrder create(PurchaseOrder order) {
        order.setId(null);
        return repository.save(order);
    }

    // Lab 3 - Test Catalog connection
    public ProductResponse testCatalogConnection(Long productId) {
        return catalogClient.getProductById(productId);
    }

    // Lab 3 Part 7 - Find product belonging to an order
    public ProductResponse getProductForOrder(Long orderId) {

        PurchaseOrder order = repository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found"
                ));

        return catalogClient.getProductById(order.getProductId());
    }
}
