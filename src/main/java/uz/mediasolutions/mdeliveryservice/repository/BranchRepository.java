package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Branch findByNameUzOrNameRu(String nameUz, String nameRu);

    boolean existsByNameUzOrNameRu(String nameUz, String nameRu);

    boolean existsByNameUzOrNameRuAndId(String nameUz, String nameRu, Long id);

    Page<Branch> findAllByAddressRuContainsIgnoreCaseOrAddressUzContainsIgnoreCaseOrNameRuContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrderByCreatedAtDesc(String aRu, String aUz, String nameUz, String nameRu, Pageable pageable);
}
