package ie.atu.cicd1orderservice.repository;

import ie.atu.cicd1orderservice.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, Long> {
}