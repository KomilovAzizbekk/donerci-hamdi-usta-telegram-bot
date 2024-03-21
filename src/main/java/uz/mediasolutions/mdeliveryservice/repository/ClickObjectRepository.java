package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.ClickObject;

public interface ClickObjectRepository extends JpaRepository<ClickObject, Long> {
}