package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;

public interface TgUserRepository extends JpaRepository<TgUser, Long> {

    Page<TgUser> findAllByNameContainsIgnoreCaseOrUsernameContainsIgnoreCaseOrPhoneNumberContainsIgnoreCaseOrderByCreatedAtDesc(String name, String username, String phoneNumber, Pageable pageable);

    Page<TgUser> findAllByOrderByCreatedAtDesc(Pageable pageable);

    TgUser findByChatId(String chatId);

    boolean existsByChatId(String chatId);
}
