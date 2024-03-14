package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.TgUserDTO;

public interface UserService {
    ApiResult<Page<TgUserDTO>> getAll(int page, int size, String search);

    ApiResult<TgUserDTO> getById(Long id);

    ApiResult<?> banUser(Long id);

    ApiResult<?> unbanUser(Long id);
}
