package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.mediasolutions.mdeliveryservice.entity.Variation;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationResDTO;

@Mapper(componentModel = "spring")
public interface VariationMapper {

    VariationResDTO toDTO(Variation variation);

    @Mapping(source = "measureUnitId", target = "measureUnit.id")
    @Mapping(source = "productId", target = "product.id")
    Variation toEntity(VariationDTO dto);

}
