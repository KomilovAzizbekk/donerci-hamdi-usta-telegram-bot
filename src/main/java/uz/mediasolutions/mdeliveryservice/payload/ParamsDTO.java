package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.Times;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParamsDTO {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "time")
    private Timestamp time;

    @JsonProperty(value = "amount")
    private float amount;

    @JsonProperty(value = "account")
    private AccountDTO account;
}
