package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariationDTO {

    private Long id;

    private Long number;

    private String nameUz;

    private String nameRu;

    private Long categoryId;

    private Integer count;

    private String imageUrl;

    private Long measureId;

    private Double price;

}
