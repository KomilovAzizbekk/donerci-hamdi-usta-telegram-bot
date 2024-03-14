package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket findByTgUserChatId(String chatId);

    boolean existsByTgUserChatId(String chatId);

}
