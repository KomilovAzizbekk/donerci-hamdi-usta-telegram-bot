package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    @JsonProperty(value = "phone")
    private String phone;

}
