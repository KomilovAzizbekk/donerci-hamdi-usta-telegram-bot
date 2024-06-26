package uz.mediasolutions.mdeliveryservice.payload.payme;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transactions {

    private String id;

    private Timestamp time;

    private Long amount;

    private Account account;

    @JsonProperty(value = "cancel_time")
    private long cancelTime;

    @JsonProperty(value = "create_time")
    private long createTime;

    @JsonProperty(value = "perform_time")
    private long performTime;

    private String transaction;

    private Integer state;

    private Integer reason;

}
