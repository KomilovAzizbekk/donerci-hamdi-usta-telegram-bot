package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.payload.ClickDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/";
    String PREPARE = "prepeare";
    String COMPLETE = "complete";

    @PostMapping(PREPARE)
    ClickDTO prepareMethod(@RequestBody ClickDTO clickDTO);

    @PostMapping(COMPLETE)
    ClickDTO completeMethod(@RequestBody ClickDTO clickDTO);
}
