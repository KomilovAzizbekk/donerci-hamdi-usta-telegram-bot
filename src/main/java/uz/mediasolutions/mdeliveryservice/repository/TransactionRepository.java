package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
