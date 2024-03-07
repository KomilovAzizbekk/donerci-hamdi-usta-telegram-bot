package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.mediasolutions.mdeliveryservice.entity.LanguagePs;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.TranslateDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LanguageServicePs {

    ApiResult<Page<LanguagePs>> getAllPaginated(int page, int size, String key);

    ResponseEntity<Map<String, String>> getAllByLanguage(String language);

    ApiResult<?> createTranslation(TranslateDto dto);

    ApiResult<?> createMainText(List<TranslateDto> dtos);

    ApiResult<?> createKey(HashMap<String, String> dto);
}
