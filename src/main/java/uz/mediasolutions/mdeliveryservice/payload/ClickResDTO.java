package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClickResDTO {
    @JsonProperty(value = "error_code")
    private Integer errorCode;
    
    @JsonProperty(value = "error_note")
    private String errorNote;

    @JsonProperty(value = "invoice_id")
    private Long invoiceId;
}
