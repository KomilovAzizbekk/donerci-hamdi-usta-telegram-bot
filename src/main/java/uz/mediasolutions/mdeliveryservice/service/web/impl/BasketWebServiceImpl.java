package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Basket;
import uz.mediasolutions.mdeliveryservice.entity.OrderProducts;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.repository.BasketRepository;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.BasketWebService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketWebServiceImpl implements BasketWebService {

    private final BasketRepository basketRepository;
    private final TgUserRepository tgUserRepository;

    @Override
    public ApiResult<BasketWebDTO> get(String chatId) {
        Basket basket = basketRepository.findByTgUserChatId(chatId);
        BasketWebDTO dto = toDTO(basket, chatId);
        return ApiResult.success(dto);
    }

    @Override
    public ApiResult<?> add(String chatId, OrderProductDTO dto) {

        return null;
    }

    @Override
    public ApiResult<?> edit(String chatId, Long id, OrderProductDTO dto) {
        return null;
    }

    private BasketWebDTO toDTO(Basket basket, String chatId) {
        BasketWebDTO.BasketWebDTOBuilder basketWebDTO = BasketWebDTO.builder();

        basketWebDTO.id(basket.getId());
        basketWebDTO.chatId(chatId);
        basketWebDTO.products(toOrderProductDTOlist(basket.getProducts(), chatId));

        return basketWebDTO.build();
    }

    private OrderProductDTO toOrderProductDTO(OrderProducts product, String chatId) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        OrderProductDTO.OrderProductDTOBuilder orderProductDTO = OrderProductDTO.builder();
        orderProductDTO.id(product.getId());
        orderProductDTO.price(product.getVariation().getPrice());
        orderProductDTO.count(product.getCount());
        orderProductDTO.measure(product.getVariation().getMeasure());
        orderProductDTO.imageUrl(product.getProduct().getImageUrl());
        if (tgUser.getLanguage().getName().equals(LanguageName.RU)) {
            orderProductDTO.name(product.getProduct().getNameRu());
            orderProductDTO.measureUnitName(product.getVariation().getMeasureUnit().getNameRu());
        } else {
            orderProductDTO.name(product.getProduct().getNameUz());
            orderProductDTO.measureUnitName(product.getVariation().getMeasureUnit().getNameUz());
        }
        return orderProductDTO.build();
    }

    private List<OrderProductDTO> toOrderProductDTOlist(List<OrderProducts> orderProducts, String chatId) {
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        for (OrderProducts product : orderProducts) {
            orderProductDTOS.add(toOrderProductDTO(product, chatId));
        }
        return orderProductDTOS;
    }
}
