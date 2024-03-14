package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationResDTO;

public interface VariationService {
    ApiResult<Page<VariationResDTO>> getAll(int page, int size, String search);

    ApiResult<VariationResDTO> getById(Long id);

    ApiResult<?> add(VariationDTO dto);

    ApiResult<?> edit(Long id, VariationDTO dto);

    ApiResult<?> delete(Long id);

}
