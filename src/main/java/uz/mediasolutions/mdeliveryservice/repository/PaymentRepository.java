package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.click.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
