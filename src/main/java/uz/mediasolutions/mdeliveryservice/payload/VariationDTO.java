package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariationDTO {

    private Long id;

    private Long number;

    private Long measureUnitId;

    private Double measure;

    private Double price;

    private Long productId;

}
