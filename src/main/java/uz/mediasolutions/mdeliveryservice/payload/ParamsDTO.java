package uz.mediasolutions.mdeliveryservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.Times;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParamsDTO {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "time")
    private long time;

    @JsonProperty(value = "amount")
    private float amount;

    @JsonProperty(value = "account")
    private AccountDTO account;
}
