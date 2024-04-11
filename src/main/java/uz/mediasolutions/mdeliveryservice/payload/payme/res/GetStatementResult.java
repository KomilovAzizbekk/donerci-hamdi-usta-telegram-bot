package uz.mediasolutions.mdeliveryservice.payload.payme.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mediasolutions.mdeliveryservice.payload.payme.Transactions;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStatementResult {

    private List<Transactions> transactions;

}
