package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByOrderByNumberAsc(Pageable pageable);

    Page<Category> findAllByNameRuContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrderByNumberAsc(String nameUz, String nameRu, Pageable pageable);

    boolean existsByNumber(Long number);

    boolean existsByNumberAndId(Long number, Long id);

    boolean existsByNameUzOrNameRu(String nameUz, String nameRu);

    boolean existsByNameUzOrNameRuAndId(String nameUz, String nameRu, Long id);
}
