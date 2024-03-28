package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymeCreateTransactionDTO {

    @JsonProperty(value = "method")
    private String method;

    @JsonProperty(value = "params")
    private ParamsDTO params;

}
