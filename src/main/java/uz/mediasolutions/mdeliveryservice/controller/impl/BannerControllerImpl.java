package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.controller.abs.BannerController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.BannerService;

@RestController
@RequiredArgsConstructor
public class BannerControllerImpl implements BannerController {

    private final BannerService bannerService;

    @Override
    public ApiResult<Page<BannerDTO>> getAll(int page, int size) {
        return bannerService.getAll(page, size);
    }

    @Override
    public ApiResult<BannerDTO> get(Long id) {
        return bannerService.get(id);
    }

    @Override
    public ApiResult<?> upload(BannerDTO dto) {
        return bannerService.upload(dto);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return bannerService.delete(id);
    }
}
