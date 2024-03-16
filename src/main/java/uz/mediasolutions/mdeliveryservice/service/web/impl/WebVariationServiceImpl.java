package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.MeasureUnit;
import uz.mediasolutions.mdeliveryservice.entity.Product;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.entity.Variation;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.MeasureUnitWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.Product2WebDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.repository.VariationRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebVariationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebVariationServiceImpl implements WebVariationService {

    private final VariationRepository variationRepository;
    private final TgUserRepository tgUserRepository;

    @Override
    public ApiResult<List<VariationWebDTO>> getAllByProductId(String chatId, Long productId) {
        if (tgUserRepository.existsByChatId(chatId)) {
            List<Variation> variations = variationRepository.findAllByProductIdOrderByNumberAsc(productId);
            List<VariationWebDTO> variationWebDTOList = toVariationWebDTOList(variations, chatId);
            return ApiResult.success(variationWebDTOList);
        } else {
            throw RestException.restThrow("USER ID NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

    private List<VariationWebDTO> toVariationWebDTOList(List<Variation> variations, String chatId) {
        if (variations == null) {
            return null;
        }

        List<VariationWebDTO> variationWebDTOS = new ArrayList<>();
        for (Variation variation : variations) {
            variationWebDTOS.add(toVariationWebDTO(variation, chatId));
        }
        return variationWebDTOS;
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
}
