package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.http.HttpEntity;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.payload.click.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.click.ClickOrderDTO;

public interface ClickService {

    HttpEntity<?> createInvoice(ClickInvoiceDTO dto, String chatId);

    HttpEntity<?> create(Double amount, String chatId);

    ClickOrderDTO prepareMethod(ClickOrderDTO clickDTO);

    ClickOrderDTO completeMethod(ClickOrderDTO clickDTO) throws TelegramApiException;

    ClickOrderDTO getInfo(ClickOrderDTO clickDTO);
}
