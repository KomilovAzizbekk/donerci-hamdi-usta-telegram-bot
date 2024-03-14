package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.OrderProducts;

public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {


}
