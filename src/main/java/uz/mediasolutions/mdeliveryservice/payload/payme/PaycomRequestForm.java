package uz.mediasolutions.mdeliveryservice.payload.payme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaycomRequestForm {

    private String method;

    private Params params;

    private Integer id;
}