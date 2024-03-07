package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.LanguageSourcePs;

import java.util.List;

public interface LanguageSourceRepositoryPs extends JpaRepository<LanguageSourcePs, Long> {
    List<LanguageSourcePs> findAllByLanguagePs_Id(Long id);

    boolean existsByLanguageAndLanguagePsId(String lang, Long langId);
}
