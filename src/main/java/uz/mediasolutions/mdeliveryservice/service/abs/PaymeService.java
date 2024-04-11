package uz.mediasolutions.mdeliveryservice.service.abs;

import net.minidev.json.JSONObject;
import uz.mediasolutions.mdeliveryservice.payload.payme.*;


public interface PaymeService {
    JSONObject payWithPayme(PaycomRequestForm requestForm, String authorization);

}
