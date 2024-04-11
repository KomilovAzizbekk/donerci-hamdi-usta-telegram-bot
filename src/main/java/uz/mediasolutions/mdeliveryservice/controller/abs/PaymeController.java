package uz.mediasolutions.mdeliveryservice.controller.abs;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.payload.payme.*;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;


@RequestMapping(PaymeController.PAYME)
public interface PaymeController {

    String PAYME = Rest.BASE_PATH + "payme/";

    @PostMapping
    JSONObject post(@RequestBody PaycomRequestForm requestForm,
                    @RequestHeader(value = "Authorization", required = false) String authorization);

}
