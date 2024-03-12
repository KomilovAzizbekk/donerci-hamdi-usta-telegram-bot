package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uz.mediasolutions.mdeliveryservice.entity.Banner;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    BannerDTO toDTO(Banner banner);

    Banner toEntity(BannerDTO bannerDTO);

}
