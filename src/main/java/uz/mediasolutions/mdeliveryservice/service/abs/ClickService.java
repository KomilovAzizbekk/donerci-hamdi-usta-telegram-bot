package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.payload.ClickDTO;

public interface ClickService {
    ClickDTO prepareMethod(ClickDTO clickDTO);

    ClickDTO completeMethod(ClickDTO clickDTO);

}
