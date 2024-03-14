package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariationWebDTO {

    private Long id;

    private Product2WebDTO product;

    private MeasureUnitWebDTO measureUnit;

    private Double measure;

    private Double price;

}
