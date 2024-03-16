package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.PaymentProviders;

public interface PaymentProvidersRepository extends JpaRepository<PaymentProviders, Long> {

}
