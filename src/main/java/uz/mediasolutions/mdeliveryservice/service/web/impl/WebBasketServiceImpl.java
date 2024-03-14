package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.*;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.*;
import uz.mediasolutions.mdeliveryservice.repository.BasketRepository;
import uz.mediasolutions.mdeliveryservice.repository.OrderProductRepository;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.repository.VariationRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebBasketService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebBasketServiceImpl implements WebBasketService {

    private final BasketRepository basketRepository;
    private final TgUserRepository tgUserRepository;
    private final VariationRepository variationRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public ApiResult<BasketWebDTO> get(String chatId) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        if (basketRepository.existsByTgUserChatId(chatId)) {
            Basket basket = basketRepository.findByTgUserChatId(chatId);
            BasketWebDTO dto = toBasketWebDTO(basket, chatId);
            return ApiResult.success(dto);
        } else {
            Basket basket = Basket.builder()
                    .tgUser(tgUser)
                    .build();
            Basket save = basketRepository.save(basket);
            BasketWebDTO dto = toBasketWebDTO(save, chatId);
            return ApiResult.success(dto);
        }
    }

    @Override
    public ApiResult<?> add(String chatId, OrderProductDTO dto) {
        OrderProducts orderProductsEntity = toOrderProductsEntity(dto);
        OrderProducts orderProducts = orderProductRepository.save(orderProductsEntity);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        if (basketRepository.existsByTgUserChatId(chatId)) {
            Basket basket = basketRepository.findByTgUserChatId(chatId);
            basket.setOrderProducts(Collections.singletonList(orderProducts));
            basketRepository.save(basket);
        } else {
            Basket basket = Basket.builder()
                    .orderProducts(Collections.singletonList(orderProducts))
                    .tgUser(tgUser)
                    .build();
            basketRepository.save(basket);
        }
        return ApiResult.success("SAVED SUCCESSFULLY");
    }

    @Override
    public ApiResult<?> edit(String chatId, Long id, OrderProductDTO dto) {
        Basket basket = basketRepository.findById(id).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        List<OrderProducts> products = basket.getOrderProducts();
        for (OrderProducts product : products) {
            if (product.getId().equals(dto.getId())) {
                if (dto.getCount() < 1) {
                    products.remove(product);
                    basket.setOrderProducts(products);
                    basketRepository.save(basket);
                    orderProductRepository.delete(product);
                } else {
                    product.setCount(dto.getCount());
                    OrderProducts orderProducts = orderProductRepository.save(product);
                    products.add(orderProducts);
                    basket.setOrderProducts(products);
                    basketRepository.save(basket);
                }
            }
        }
        return ApiResult.success("EDITED SUCCESSFULLY");
    }




    private OrderProducts toOrderProductsEntity(OrderProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Variation variation = variationRepository.findById(dto.getVariationId()).orElseThrow(
                () -> RestException.restThrow("VARIATION ID NOT FOUND", HttpStatus.BAD_REQUEST));

        OrderProducts.OrderProductsBuilder builder = OrderProducts.builder();
        builder.count(dto.getCount());
        builder.variation(variation);
        return builder.build();
    }

    private BasketWebDTO toBasketWebDTO(Basket basket, String chatId) {
        if (basket == null) {
            return null;
        }

        BasketWebDTO.BasketWebDTOBuilder basketWebDTO = BasketWebDTO.builder();

        basketWebDTO.id(basket.getId());
        basketWebDTO.orderProducts(toOrderProductResDTOlist(basket.getOrderProducts(), chatId));

        return basketWebDTO.build();
    }

    private OrderProductResDTO toOrderProductResDTO(OrderProducts product, String chatId) {
        if (product == null) {
            return null;
        }

        OrderProductResDTO.OrderProductResDTOBuilder builder = OrderProductResDTO.builder();
        builder.id(product.getId());
        builder.count(product.getCount());
        builder.variation(toVariationWebDTO(product.getVariation(), chatId));
        return builder.build();
    }

    private List<OrderProductResDTO> toOrderProductResDTOlist(List<OrderProducts> orderProducts, String chatId) {
        if (orderProducts == null) {
            return null;
        }

        List<OrderProductResDTO> orderProductDTOS = new ArrayList<>();
        for (OrderProducts product : orderProducts) {
            orderProductDTOS.add(toOrderProductResDTO(product, chatId));
        }
        return orderProductDTOS;
    }

    private MeasureUnitWebDTO toMeasureUnitDTO(MeasureUnit measureUnit, String chatId) {
        if (measureUnit == null) {
            return null;
        }

        TgUser tgUser = tgUserRepository.findByChatId(chatId);

        MeasureUnitWebDTO.MeasureUnitWebDTOBuilder builder = MeasureUnitWebDTO.builder();
        builder.id(measureUnit.getId());
        if (tgUser.getLanguage().getName().equals(LanguageName.UZ)) {
            builder.name(measureUnit.getNameUz());
        } else {
            builder.name(measureUnit.getNameRu());
        }
        return builder.build();
    }

    private Product2WebDTO toProduct2WebDTO(Product product, String chatId) {
        if (product == null) {
            return null;
        }

        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        Product2WebDTO.Product2WebDTOBuilder builder = Product2WebDTO.builder();
        builder.imageUrl(product.getImageUrl());
        builder.id(product.getId());
        if (tgUser.getLanguage().getName().equals(LanguageName.UZ)) {
            builder.name(product.getNameUz());
        } else {
            builder.name(product.getNameRu());
        }
        return builder.build();
    }

    private VariationWebDTO toVariationWebDTO(Variation variation, String chatId) {
        if (variation == null) {
            return null;
        }

        VariationWebDTO.VariationWebDTOBuilder builder = VariationWebDTO.builder();
        builder.price(variation.getPrice());
        builder.measure(variation.getMeasure());
        builder.measureUnit(toMeasureUnitDTO(variation.getMeasureUnit(), chatId));
        builder.id(variation.getId());
        builder.product(toProduct2WebDTO(variation.getProduct(), chatId));
        return builder.build();
    }
}