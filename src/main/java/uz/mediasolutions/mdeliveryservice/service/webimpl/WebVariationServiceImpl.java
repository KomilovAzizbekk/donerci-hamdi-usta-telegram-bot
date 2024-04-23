package uz.mediasolutions.mdeliveryservice.service.webimpl;

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
import uz.mediasolutions.mdeliveryservice.mapper.UniversalMapper;
import uz.mediasolutions.mdeliveryservice.payload.MeasureUnitWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.Product2WebDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.repository.VariationRepository;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebVariationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebVariationServiceImpl implements WebVariationService {

    private final VariationRepository variationRepository;
    private final TgUserRepository tgUserRepository;
    private final UniversalMapper universalMapper;

    @Override
    public ApiResult<List<VariationWebDTO>> getAllByProductId(String chatId, Long productId) {
        if (tgUserRepository.existsByChatId(chatId)) {
            List<Variation> variations = variationRepository.findAllByProductIdAndActiveIsTrueOrderByNumberAsc(productId);
            List<VariationWebDTO> variationWebDTOList = universalMapper.toVariationWebDTOList(variations, chatId);
            return ApiResult.success(variationWebDTOList);
        } else {
            throw RestException.restThrow("USER ID NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

}
