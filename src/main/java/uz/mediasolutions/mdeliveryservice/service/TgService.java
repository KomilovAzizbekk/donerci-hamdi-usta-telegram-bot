package uz.mediasolutions.mdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;
import uz.mediasolutions.mdeliveryservice.enums.StepName;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.utills.constants.Message;

@Service
@RequiredArgsConstructor
public class TgService extends TelegramLongPollingBot {

    private final MakeService makeService;
    private final TgUserRepository tgUserRepository;

    @Override
    public String getBotUsername() {
        return "uygogo_bot";
//        return "sakaka_bot";
    }

    @Override
    public String getBotToken() {
        return "5049026983:AAHjxVS4KdTmMLp4x_ir9khH4w1tB4h6pPQ";
//        return "6052104473:AAEscLILevwPMcG_00PYqAf-Kpb7eIUCIGg";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String chatId = makeService.getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        boolean existsByChatId = tgUserRepository.existsByChatId(chatId);

        System.out.println(update);

        if (existsByChatId && tgUser.isBanned()) {
            execute(new SendMessage(chatId, makeService.getMessage(Message.YOU_ARE_BANNED,
                    makeService.getUserLanguage(chatId))));
        } else if (update.hasMessage() && update.getMessage().hasText() &&
                update.getMessage().getText().equals("/start") &&
                !tgUserRepository.existsByChatId(chatId)) {
            execute(makeService.whenStart(update));
        } else if (update.hasMessage() && update.getMessage().hasText() &&
                update.getMessage().getText().equals("/start") &&
                tgUserRepository.existsByChatId(chatId)) {
            makeService.setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
            if (tgUser.getLanguage().getName().equals(LanguageName.UZ))
                execute(makeService.whenUz(update));
            else
                execute(makeService.whenRu(update));
        } else {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                if (makeService.getUserStep(chatId).equals(StepName.MAIN_MENU) &&
                        text.equals(makeService.getMessage(Message.UZBEK, "UZ"))) {
                    execute(makeService.whenUz(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.MAIN_MENU) &&
                        text.equals(makeService.getMessage(Message.RUSSIAN, "UZ"))) {
                    execute(makeService.whenRu(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHOOSE_FROM_MAIN_MENU) &&
                        text.equals(makeService.getMessage(Message.MENU_SUG_COMP, makeService.getUserLanguage(chatId)))) {
                    execute(makeService.whenSuggestComplaint(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.SEND_SUGGESTION_COMPLAINT)) {
                    execute(makeService.whenSendSuggestComplaint(update));
                    execute(makeService.whenSendSuggestComplaintToChannel(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHOOSE_FROM_MAIN_MENU) &&
                        text.equals(makeService.getMessage(Message.MENU_SETTINGS, makeService.getUserLanguage(chatId)))) {
                    execute(makeService.whenSettings1(update));
                    execute(makeService.whenSettings2(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHANGE_NAME)) {
                    execute(makeService.whenChangeName2(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHANGE_PHONE_NUMBER)) {
                    execute(makeService.whenChangePhoneNumber2(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHANGE_LANGUAGE)) {
                    execute(makeService.whenChangeLanguage2(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.ORDER_REGISTER_PHONE)) {
                    execute(makeService.whenOrderRegPhone1(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.ORDER_LOCATION)) {
                    execute(makeService.whenOrderLocation1(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.LEAVE_COMMENT) &&
                        (text.equals(makeService.getMessage(Message.CLICK, makeService.getUserLanguage(chatId))) ||
                                text.equals(makeService.getMessage(Message.PAYME, makeService.getUserLanguage(chatId))) ||
                                text.equals(makeService.getMessage(Message.CASH, makeService.getUserLanguage(chatId))))) {
                    execute(makeService.whenLeaveComment(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.GO_TO_PAYMENT)) {
                    execute(makeService.whenGoPayment(update));
                }
            } else if (update.hasMessage() && update.getMessage().hasContact()) {
                if (makeService.getUserStep(chatId).equals(StepName.INCORRECT_PHONE_FORMAT)) {
                    execute(makeService.whenIncorrectPhoneFormat(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.INCORRECT_PHONE_FORMAT_1)) {
                    execute(makeService.whenIncorrectPhoneFormat1(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.CHANGE_PHONE_NUMBER)) {
                    execute(makeService.whenChangePhoneNumber2(update));
                } else if (makeService.getUserStep(chatId).equals(StepName.ORDER_LOCATION)) {
                    execute(makeService.whenOrderLocation1(update));
                }
            } else if (update.hasMessage() && update.getMessage().hasLocation()) {
                if (makeService.getUserStep(chatId).equals(StepName.CHOOSE_PAYMENT)) {
                    execute(makeService.whenChoosePayment(update));
                }
            } else if (update.hasCallbackQuery()) {
                String data = update.getCallbackQuery().getData();
                if (data.equals("changeName")) {
                    execute(makeService.whenChangeName1(update));
                } else if (data.equals("changePhone")) {
                    execute(makeService.deleteMessageForCallback(update));
                    execute(makeService.whenChangePhoneNumber1(update));
                } else if (data.equals("changeLanguage")) {
                    execute(makeService.deleteMessageForCallback(update));
                    execute(makeService.whenChangeLanguage1(update));
                }
            }
        }
    }
}
