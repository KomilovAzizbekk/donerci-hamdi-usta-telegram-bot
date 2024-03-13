package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Banner;
import uz.mediasolutions.mdeliveryservice.entity.Basket;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket findByTgUserChatId(String chatId);

}
