package uz.mediasolutions.mdeliveryservice.mapper;

import uz.mediasolutions.mdeliveryservice.entity.*;
import uz.mediasolutions.mdeliveryservice.payload.*;

import java.util.List;

public interface UniversalMapper {

    VariationWebDTO toVariationWebDTO(Variation variation, String chatId);

    MeasureUnitWebDTO toMeasureUnitDTO(MeasureUnit measureUnit, String chatId);

    Product2WebDTO toProduct2WebDTO(Product product, String chatId);

    OrderProductResDTO toOrderProductResDTO(OrderProducts product, String chatId);

    List<OrderProductResDTO> toOrderProductResDTOlist(List<OrderProducts> orderProducts, String chatId);

    BasketWebDTO toBasketWebDTO(Basket basket, String chatId);

    double totalPrice(List<OrderProducts> orderProducts);

    List<OrderProducts> toOrderProductsEntityList(List<OrderProductDTO> dtoList);

    OrderProducts toOrderProductsEntity(OrderProductDTO dto);

    OrderWebDTO toOrderWebDTO(Order order, String chatId);

    List<OrderWebDTO> toOrderWebDTOList(List<Order> orders, String chatId);

    BranchWebDTO toBranchWebDTO(Branch branch, String chatId);

}
