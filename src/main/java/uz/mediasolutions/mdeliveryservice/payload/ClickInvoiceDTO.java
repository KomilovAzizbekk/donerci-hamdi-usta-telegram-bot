package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClickInvoiceDTO {
    /**
     * ID of the service
     */
    @JsonProperty(value = "service_id")
    private Integer serviceId;
    /**
     * Payment Amount (in soums)
     */
    @NotNull
    @JsonProperty(value = "amount")
    private Float amount;

    /**
     * Phone number
     */

    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    /**
     * Order ID (for online shopping) / personal account / login in the billing of the supplier
     */
    @JsonProperty(value = "merchant_trans_id")
    private String merchantTransId;
}
