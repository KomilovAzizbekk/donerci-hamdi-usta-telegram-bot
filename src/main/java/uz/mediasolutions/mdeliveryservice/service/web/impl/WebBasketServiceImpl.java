package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.UniversalMapper;
import uz.mediasolutions.mdeliveryservice.payload.*;
import uz.mediasolutions.mdeliveryservice.repository.BasketRepository;
import uz.mediasolutions.mdeliveryservice.repository.OrderProductRepository;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebBasketService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebBasketServiceImpl implements WebBasketService {

    private final BasketRepository basketRepository;
    private final TgUserRepository tgUserRepository;
    private final OrderProductRepository orderProductRepository;
    private final UniversalMapper universalMapper;

    @Override
    public ApiResult<BasketWebDTO> get(String chatId) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        if (basketRepository.existsByTgUserChatId(chatId)) {
            Basket basket = basketRepository.findByTgUserChatId(chatId);
            BasketWebDTO dto = universalMapper.toBasketWebDTO(basket, chatId);
            return ApiResult.success(dto);
        } else {
            Basket basket = Basket.builder()
                    .tgUser(tgUser)
                    .totalPrice(0D)
                    .build();
            Basket save = basketRepository.save(basket);
            BasketWebDTO dto = universalMapper.toBasketWebDTO(save, chatId);
            return ApiResult.success(dto);
        }
    }

    @Override
    public ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) {
        List<OrderProducts> orderProductsEntity = universalMapper.toOrderProductsEntityList(dtoList);
        List<OrderProducts> orderProducts = orderProductRepository.saveAll(orderProductsEntity);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        if (basketRepository.existsByTgUserChatId(chatId)) {
            Basket basket = basketRepository.findByTgUserChatId(chatId);
            basket.setOrderProducts(orderProducts);
            basket.setTotalPrice(universalMapper.totalPrice(orderProducts));
            basketRepository.save(basket);
        } else {
            Basket basket = Basket.builder()
                    .orderProducts(orderProducts)
                    .tgUser(tgUser)
                    .totalPrice(universalMapper.totalPrice(orderProducts))
                    .build();
            basketRepository.save(basket);
        }
        return ApiResult.success("SAVED SUCCESSFULLY");
    }


}
