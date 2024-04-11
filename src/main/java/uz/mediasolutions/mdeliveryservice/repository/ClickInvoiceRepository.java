package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.click.ClickInvoice;

public interface ClickInvoiceRepository extends JpaRepository<ClickInvoice, Long> {

}
