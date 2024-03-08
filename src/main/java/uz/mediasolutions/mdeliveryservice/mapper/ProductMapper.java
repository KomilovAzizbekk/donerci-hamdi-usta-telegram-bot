package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.mediasolutions.mdeliveryservice.entity.Product;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationResDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductVariationResDTO toDTO(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "measureId", target = "variation.measure.id")
    Product toEntity(ProductVariationDTO productDTO);

}
