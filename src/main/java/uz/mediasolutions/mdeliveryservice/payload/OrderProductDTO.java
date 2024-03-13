package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDTO {

    private Long id;

    private String name;

    private String imageUrl;

    private Double price;

    private Integer count;

    private Double measure;

    private String measureUnitName;

}
