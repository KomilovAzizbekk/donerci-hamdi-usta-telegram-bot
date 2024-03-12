package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariationResDTO {

    private Long id;

    private Long number;

    private MeasureUnitDTO measureUnit;

    private Double measure;

    private Double price;

    private Integer count;

    private ProductDTO product;

}
