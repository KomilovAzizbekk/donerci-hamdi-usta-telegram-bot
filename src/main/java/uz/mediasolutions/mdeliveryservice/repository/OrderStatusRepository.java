package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {


}
