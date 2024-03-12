package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Banner;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.BannerMapper;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.repository.BannerRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.BannerService;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public ApiResult<Page<BannerDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Banner> banners = bannerRepository.findAllByOrderByNumberAsc(pageable);
        Page<BannerDTO> dtos = banners.map(bannerMapper::toDTO);
        return ApiResult.success(dtos);
    }

    @Override
    public ApiResult<BannerDTO> get(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        BannerDTO dto = bannerMapper.toDTO(banner);
        return ApiResult.success(dto);
    }

    public ApiResult<?> upload(BannerDTO dto) {
        Banner banner = Banner.builder()
                .number(dto.getNumber())
                .imageUrl(dto.getImageUrl())
                .build();
        bannerRepository.save(banner);
        return ApiResult.success("SAVED SUCCESSFULLY");
    }

    @Override
    public ApiResult<?> delete(Long id) {
        try {
            bannerRepository.deleteById(id);
            return ApiResult.success("DELETED SUCCESSFULLY");
        } catch (Exception e) {
            throw RestException.restThrow("CANNOT DELETE", HttpStatus.CONFLICT);
        }
    }

}
