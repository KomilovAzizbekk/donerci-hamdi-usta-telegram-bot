package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Step;
import uz.mediasolutions.mdeliveryservice.enums.StepName;

public interface StepRepository extends JpaRepository<Step, Long> {

    Step findByName(StepName stepName);

}
