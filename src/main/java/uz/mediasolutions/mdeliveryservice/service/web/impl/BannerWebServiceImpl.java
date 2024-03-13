package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Banner;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.BannerMapper;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.repository.BannerRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.BannerWebService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerWebServiceImpl implements BannerWebService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public ApiResult<List<BannerDTO>> get() {
        List<Banner> banners = bannerRepository.findAllByOrderByNumberAsc();
        List<BannerDTO> dtoList = bannerMapper.toDTOList(banners);
        return ApiResult.success(dtoList);
    }
}
