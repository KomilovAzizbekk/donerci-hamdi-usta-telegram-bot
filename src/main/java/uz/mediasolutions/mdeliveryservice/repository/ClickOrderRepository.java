package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.ClickOrder;

public interface ClickOrderRepository extends JpaRepository<ClickOrder, Long> {
}