package uz.mediasolutions.mdeliveryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDTO {

    private Long id;

    private String nameUz;

    private String nameRu;

    private Double lon;

    private Double lat;

    private String addressUz;

    private String addressRu;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private boolean isClosesAfterMn;

    private boolean isActive;

}
