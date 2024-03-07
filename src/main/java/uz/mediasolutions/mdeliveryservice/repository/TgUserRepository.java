package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;

public interface TgUserRepository extends JpaRepository<TgUser, Long> {
    TgUser findByChatId(String chatId);

    boolean existsByChatId(String chatId);
}
