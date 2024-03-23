package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWebDTO {

    private Long id;

    private String name;

    private String imageUrl;

    private float price;

    private boolean oneVariation;

}
