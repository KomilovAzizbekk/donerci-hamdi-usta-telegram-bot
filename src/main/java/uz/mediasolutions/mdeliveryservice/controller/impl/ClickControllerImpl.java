package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.ClickController;
import uz.mediasolutions.mdeliveryservice.payload.ClickDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

@RestController
@RequiredArgsConstructor
public class ClickControllerImpl implements ClickController {

    //https://docs.click.uz/click-api-request/

    private final ClickService clickService;

    @Override
    public ClickDTO prepareMethod(ClickDTO clickDTO) {
        return clickService.prepareMethod(clickDTO);
    }

    @Override
    public ClickDTO completeMethod(ClickDTO clickDTO) {
        return clickService.completeMethod(clickDTO);
    }
}
