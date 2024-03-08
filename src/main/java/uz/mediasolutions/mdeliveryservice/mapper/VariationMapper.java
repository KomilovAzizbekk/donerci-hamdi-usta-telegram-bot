package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.mediasolutions.mdeliveryservice.entity.Variation;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;

@Mapper(componentModel = "spring")
public interface VariationMapper {

    @Mapping(source = "measure.id", target = "measureId")
    VariationDTO toDTO(Variation variation);

    @Mapping(source = "measureId", target = "measure.id")
    Variation toEntity(ProductVariationDTO dto);

    @Mapping(source = "measureId", target = "measure.id")
    Variation toVarEntity(VariationDTO dto);

}
