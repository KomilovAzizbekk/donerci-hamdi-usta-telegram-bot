package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.mediasolutions.mdeliveryservice.entity.click.ClickOrder;
import uz.mediasolutions.mdeliveryservice.payload.click.ClickOrderDTO;

/**
 * This interface not documented :(
 */
@Mapper(componentModel = "spring")
public interface ClickOrderMapper {
    @Mapping(target = "transactionId", source = "click_trans_id")
    @Mapping(target = "serviceId", source = "service_id")
    @Mapping(target = "paydocId", source = "click_paydoc_id")
    @Mapping(target = "merchantTransactionId", source = "merchant_trans_id")
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "errorNote", source = "error_note")
    @Mapping(target = "id", ignore = true)
    ClickOrder toEntity(ClickOrderDTO clickOrderDTO);
}
