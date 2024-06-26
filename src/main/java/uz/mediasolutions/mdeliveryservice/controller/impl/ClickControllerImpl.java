package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.controller.abs.ClickController;
import uz.mediasolutions.mdeliveryservice.payload.click.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.click.ClickOrderDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

@RestController
@RequiredArgsConstructor
public class ClickControllerImpl implements ClickController {

    private final ClickService clickService;

    @Override
    public HttpEntity<?> createInvoice(ClickInvoiceDTO dto, String chatId) {
        return clickService.createInvoice(dto, chatId);
    }

    @Override
    public HttpEntity<?> create(Double amount, String chatId) {
        return clickService.create(amount, chatId);
    }

    @Override
    public ClickOrderDTO prepareMethod(ClickOrderDTO clickDTO) {
        return clickService.prepareMethod(clickDTO);
    }

    @Override
    public ClickOrderDTO completeMethod(ClickOrderDTO clickDTO) throws TelegramApiException {
        return clickService.completeMethod(clickDTO);
    }

    @Override
    public ClickOrderDTO getInfo(ClickOrderDTO clickDTO) {
        return clickService.getInfo(clickDTO);
    }
}
