package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.http.HttpEntity;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickOrderDTO;

import java.io.IOException;

public interface ClickService {

    HttpEntity<?> createInvoice(ClickInvoiceDTO dto, String chatId);

    HttpEntity<?> create(Double amount, String chatId);

    ClickOrderDTO prepareMethod(ClickOrderDTO clickDTO) throws TelegramApiException;

    ClickOrderDTO completeMethod(ClickOrderDTO clickDTO);

    ClickOrderDTO getInfo(ClickOrderDTO clickDTO);
}
