package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;

public interface VariationService {
    ApiResult<Page<VariationDTO>> getAll(int page, int size);

    ApiResult<VariationDTO> getById(Long id);

    ApiResult<?> add(VariationDTO dto);

    ApiResult<?> edit(Long id, VariationDTO dto);

    ApiResult<?> delete(Long id);

}
