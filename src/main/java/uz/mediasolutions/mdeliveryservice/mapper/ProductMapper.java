package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.mediasolutions.mdeliveryservice.entity.Product;
import uz.mediasolutions.mdeliveryservice.payload.ProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductResDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResDTO toDTO(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDTO productDTO);

}
