package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uz.mediasolutions.mdeliveryservice.entity.MeasureUnit;
import uz.mediasolutions.mdeliveryservice.payload.MeasureUnitDTO;

@Mapper(componentModel = "spring")
public interface MeasureUnitMapper {

    MeasureUnitDTO toDTO(MeasureUnit measureUnit);

    MeasureUnit toEntity(MeasureUnitDTO measureUnitDTO);

}
