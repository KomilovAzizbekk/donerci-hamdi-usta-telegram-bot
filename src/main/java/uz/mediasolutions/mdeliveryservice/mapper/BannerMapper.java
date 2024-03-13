package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uz.mediasolutions.mdeliveryservice.entity.Banner;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    BannerDTO toDTO(Banner banner);

    Banner toEntity(BannerDTO bannerDTO);

    List<BannerDTO> toDTOList(List<Banner> banners);

}
