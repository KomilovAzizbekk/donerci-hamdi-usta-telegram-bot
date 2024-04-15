package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mediasolutions.mdeliveryservice.entity.payme.OrderTransaction;
import uz.mediasolutions.mdeliveryservice.enums.TransactionState;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long> {

    Optional<OrderTransaction> findByPaycomId(String id);

    boolean existsByPaycomIdAndOrderId(String id, Long orderId);

    boolean existsByOrderId(Long id);

    List<OrderTransaction> findAllByStateAndCreateTimeBetween(TransactionState state, Timestamp from, Timestamp to);

}
