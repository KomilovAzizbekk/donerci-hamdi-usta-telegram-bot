package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.PaymentProviders;
import uz.mediasolutions.mdeliveryservice.enums.ProviderName;

public interface PaymentProvidersRepository extends JpaRepository<PaymentProviders, Long> {

    PaymentProviders findByName(ProviderName name);

}
