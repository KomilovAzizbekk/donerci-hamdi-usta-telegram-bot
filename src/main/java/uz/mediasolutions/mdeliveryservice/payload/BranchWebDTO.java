package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchWebDTO {

    private Long id;

    private String name;

    private Double lon;

    private Double lat;

    private String address;

    private LocalTime openingTime;

    private LocalTime closingTime;

}
