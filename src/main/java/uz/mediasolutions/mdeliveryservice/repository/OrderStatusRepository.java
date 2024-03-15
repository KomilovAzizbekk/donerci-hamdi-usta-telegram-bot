package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.OrderStatus;
import uz.mediasolutions.mdeliveryservice.enums.OrderStatusName;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus findByName(OrderStatusName name);

}
