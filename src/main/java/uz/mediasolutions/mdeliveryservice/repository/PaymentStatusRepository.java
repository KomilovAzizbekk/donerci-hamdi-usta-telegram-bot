package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.PaymentStatus;
import uz.mediasolutions.mdeliveryservice.enums.PaymentStatusName;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {

    PaymentStatus findByName(PaymentStatusName name);

}
