package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Language;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByName(LanguageName languageName);
}
