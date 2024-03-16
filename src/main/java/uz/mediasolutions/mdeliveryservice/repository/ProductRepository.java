package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Category;
import uz.mediasolutions.mdeliveryservice.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByOrderByNumberAsc(Pageable pageable);

    Page<Product> findAllByActiveIsTrueOrderByNumberAsc(Pageable pageable);

    Page<Product> findAllByDescriptionRuContainsIgnoreCaseOrDescriptionUzContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrNameRuContainsIgnoreCaseOrCategoryNameRuContainsIgnoreCaseOrCategoryNameUzContainsIgnoreCaseOrderByNumberAsc(String dRu, String dUz, String nameUz, String nameRu, String catNameRu, String catNameUz, Pageable pageable);

    Page<Product> findAllByActiveIsTrueAndDescriptionRuContainsIgnoreCaseOrDescriptionUzContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrNameRuContainsIgnoreCaseOrCategoryNameRuContainsIgnoreCaseOrCategoryNameUzContainsIgnoreCaseOrderByNumberAsc(String dRu, String dUz, String nameUz, String nameRu, String catNameRu, String catNameUz, Pageable pageable);

    boolean existsByNumberAndCategoryId(Long number, Long categoryId);

    boolean existsByNumberAndId(Long number, Long id);

    boolean existsByNameUzOrNameRu(String nameUz, String nameRu);

    boolean existsByNameUzOrNameRuAndId(String nameUz, String nameRu, Long id);

    List<Product> findAllByCategoryIdAndVariationsIsNotEmptyAndActiveIsTrueAndCategoryActiveIsTrueOrderByNumberAsc(Long categoryId);
}
