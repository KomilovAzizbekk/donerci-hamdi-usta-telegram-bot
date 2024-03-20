package uz.mediasolutions.mdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.mediasolutions.mdeliveryservice.entity.*;
import uz.mediasolutions.mdeliveryservice.enums.LanguageName;
import uz.mediasolutions.mdeliveryservice.enums.ProviderName;
import uz.mediasolutions.mdeliveryservice.enums.StepName;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.repository.*;
import uz.mediasolutions.mdeliveryservice.utills.constants.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MakeService {

    private final LanguageRepositoryPs languageRepositoryPs;
    private final TgUserRepository tgUserRepository;
    private final StepRepository stepRepository;
    private final LanguageRepository languageRepository;
    private final OrderRepository orderRepository;
    private final PaymentProvidersRepository paymentProvidersRepository;

    public static final String SUGGEST_COMPLAINT_CHANNEL_ID = "-1001903287909";
    public static final String LINK = "https://restoran-telegram-web-app.netlify.app/";
    public static final String UZ = "UZ";
    public static final String RU = "RU";

    public String getMessage(String key, String language) {
        List<LanguagePs> allByLanguage = languageRepositoryPs.findAll();
        if (!allByLanguage.isEmpty()) {
            for (LanguagePs languagePs : allByLanguage) {
                for (LanguageSourcePs languageSourceP : languagePs.getLanguageSourcePs()) {
                    if (languageSourceP.getTranslation() != null &&
                            languageSourceP.getLanguage().equals(language) &&
                            languagePs.getKey().equals(key)) {
                        return languageSourceP.getTranslation();
                    }
                }
            }
        }
        return null;
    }

    public void setUserStep(String chatId, StepName stepName) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        tgUser.setStep(stepRepository.findByName(stepName));
        tgUserRepository.save(tgUser);
    }

    public StepName getUserStep(String chatId) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        return tgUser.getStep().getName();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\+998[1-9]\\d{8}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public String getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId().toString();
        }
        return "";
    }

    public String getUsername(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getUserName();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getUserName();
        }
        return "";
    }

    public String getUserLanguage(String chatId) {
        if (tgUserRepository.existsByChatId(chatId)) {
            TgUser tgUser = tgUserRepository.findByChatId(chatId);
            return tgUser.getLanguage().getName().name();
        } else
            return "UZ";
    }

    public SendMessage whenStart(Update update) {
        String chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.LANG_SAME_FOR_2_LANG,
                getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forStart());
        TgUser tgUser = TgUser.builder().chatId(chatId)
                .admin(false)
                .registered(false)
                .banned(false)
                .username(getUsername(update))
                .chatId(chatId)
                .build();
        tgUserRepository.save(tgUser);
        setUserStep(chatId, StepName.MAIN_MENU);
        return sendMessage;
    }

    private ReplyKeyboardMarkup forStart() {
        String chatId = getChatId(new Update());

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();

        button1.setText(getMessage(Message.UZBEK, getUserLanguage(chatId)));
        button2.setText(getMessage(Message.RUSSIAN, getUserLanguage(chatId)));

        row1.add(button1);
        row1.add(button2);

        rowList.add(row1);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public SendMessage whenUz(Update update) {
        return getSendMessage(update, LanguageName.UZ);
    }

    public SendMessage whenRu(Update update) {
        return getSendMessage(update, LanguageName.RU);
    }

    private SendMessage getSendMessage(Update update, LanguageName languageName) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        Language language = languageRepository.findByName(languageName);
        tgUser.setLanguage(language);
        tgUserRepository.save(tgUser);
        SendMessage sendMessage = new SendMessage(getChatId(update),
                String.format(getMessage(Message.MENU_MSG, getUserLanguage(chatId)), getUsername(update)));
        sendMessage.setReplyMarkup(forMainMenu(chatId));
        setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
        return sendMessage;
    }

    public ReplyKeyboardMarkup forMainMenu(String chatId) {
        TgUser tgUser = tgUserRepository.findByChatId(chatId);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();
        KeyboardButton button4 = new KeyboardButton();

        button1.setText(getMessage(Message.MENU_WEBSITE, getUserLanguage(chatId)));
        button2.setText(getMessage(Message.MENU_SUG_COMP, getUserLanguage(chatId)));
        button3.setText(getMessage(Message.MENU_MY_ORDERS, getUserLanguage(chatId)));
        button4.setText(getMessage(Message.MENU_SETTINGS, getUserLanguage(chatId)));

        if (tgUser.getLanguage().getName().equals(LanguageName.UZ)) {
            button1.setWebApp(new WebAppInfo(LINK + chatId + "/" + UZ));
            button3.setWebApp(new WebAppInfo(LINK + chatId + "/" + UZ));
        } else {
            button1.setWebApp(new WebAppInfo(LINK + chatId + "/" + RU));
            button3.setWebApp(new WebAppInfo(LINK + chatId + "/" + RU));
        }

        row1.add(button1);
        row2.add(button2);
        row2.add(button3);
        row3.add(button4);

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public SendMessage whenSuggestComplaint(Update update) {
        String chatId = getChatId(update);

        setUserStep(chatId, StepName.SEND_SUGGESTION_COMPLAINT);
        SendMessage sendMessage =  new SendMessage(chatId, getMessage(Message.SEND_SUG_COMP,
                getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        return sendMessage;
    }

    public SendMessage whenSendSuggestComplaint(Update update) {
        String chatId = getChatId(update);
        setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.RESPONSE_SUG_COMP,
                getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forMainMenu(chatId));
        return sendMessage;
    }

    public SendMessage whenSendSuggestComplaintToChannel(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        String username = String.format("<a href='%s' target='_blank'>",
                "https://t.me/" + update.getMessage().getFrom().getUserName()) +
                tgUser.getUsername() + "</a>";
        String phoneNumber = tgUser.getPhoneNumber();
        String text = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage(SUGGEST_COMPLAINT_CHANNEL_ID,
                String.format(getMessage(Message.SUGGEST_COMPLAINT,
                getUserLanguage(chatId)), username, phoneNumber, text));
        sendMessage.enableHtml(true);
        return sendMessage;
    }

    public SendMessage whenSettings1(Update update) {
        String chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getMessage(Message.SETTINGS, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        setUserStep(chatId, StepName.MENU_SETTINGS);
        return sendMessage;
    }

    public SendMessage whenSettings2(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        String language = getUserLanguage(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(
                String.format(getMessage(Message.USER_INFO, language),
                        tgUser.getName() != null ? tgUser.getName() : getMessage(Message.NOT_EXISTS, language),
                        tgUser.getPhoneNumber() != null ? tgUser.getPhoneNumber() : getMessage(Message.NOT_EXISTS, language),
                        tgUser.getLanguage().getId() == 1 ?
                                getMessage(Message.UZBEK, "UZ") :
                                getMessage(Message.RUSSIAN, "UZ")));
        sendMessage.setReplyMarkup(forSettings(update));
        return sendMessage;
    }

    private InlineKeyboardMarkup forSettings(Update update) {
        String chatId = getChatId(update);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();

        button1.setText(getMessage(Message.CHANGE_NAME, getUserLanguage(chatId)));
        button2.setText(getMessage(Message.CHANGE_PHONE_NUMBER, getUserLanguage(chatId)));
        button3.setText(getMessage(Message.CHANGE_LANGUAGE, getUserLanguage(chatId)));

        button1.setCallbackData("changeName");
        button2.setCallbackData("changePhone");
        button3.setCallbackData("changeLanguage");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();

        row1.add(button1);
        row2.add(button2);
        row3.add(button3);

        rowsInline.add(row1);
        rowsInline.add(row2);
        rowsInline.add(row3);

        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

    public EditMessageText whenChangeName1(Update update) {
        String chatId = getChatId(update);
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setText(getMessage(Message.ENTER_NAME, getUserLanguage(chatId)));
        setUserStep(chatId, StepName.CHANGE_NAME);
        return editMessageText;
    }

    public SendMessage whenChangeName2(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        tgUser.setName(update.getMessage().getText());
        tgUserRepository.save(tgUser);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getMessage(Message.NAME_CHANGED, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forMainMenu(chatId));
        setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
        return sendMessage;
    }

    public SendMessage whenChangePhoneNumber1(Update update) {
        String chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.ENTER_PHONE_NUMBER, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forPhoneNumber(chatId));
        setUserStep(chatId, StepName.CHANGE_PHONE_NUMBER);
        return sendMessage;
    }

    public SendMessage whenChangePhoneNumber2(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);

        if (update.getMessage().hasText()) {
            if (isValidPhoneNumber(update.getMessage().getText())) {
                String phoneNumber = update.getMessage().getText();
                tgUser.setPhoneNumber(phoneNumber);
                tgUserRepository.save(tgUser);
                return executeChangePhoneNumber(update);
            } else {
                SendMessage sendMessage = new SendMessage(getChatId(update),
                        getMessage(Message.INCORRECT_PHONE_FORMAT, getUserLanguage(chatId)));
                sendMessage.setReplyMarkup(forPhoneNumber(chatId));
                setUserStep(chatId, StepName.INCORRECT_PHONE_FORMAT);
                return sendMessage;
            }
        } else {
            String phoneNumber = update.getMessage().getContact().getPhoneNumber();
            phoneNumber = phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber;
            tgUser.setPhoneNumber(phoneNumber);
            tgUserRepository.save(tgUser);
            return executeChangePhoneNumber(update);
        }
    }

    public SendMessage whenIncorrectPhoneFormat(Update update) {
        return whenChangePhoneNumber2(update);
    }


    public SendMessage whenIncorrectPhoneFormat1(Update update) {
        return whenOrderLocation1(update);
    }

    private ReplyKeyboardMarkup forPhoneNumber(String chatId) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();

        button1.setText(getMessage(Message.SHARE_PHONE_NUMBER, getUserLanguage(chatId)));
        button1.setRequestContact(true);

        row1.add(button1);

        rowList.add(row1);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    private SendMessage executeChangePhoneNumber(Update update) {
        String chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.PHONE_NUMBER_CHANGED,
                getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forMainMenu(chatId));
        setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
        return sendMessage;
    }

    public DeleteMessage deleteMessageForCallback(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(getChatId(update));
        deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        return deleteMessage;
    }

    public SendMessage whenChangeLanguage1(Update update) {
        String chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage(chatId,
                getMessage(Message.LANG_SAME_FOR_2_LANG, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forStart());
        setUserStep(chatId, StepName.CHANGE_LANGUAGE);
        return sendMessage;
    }

    public SendMessage whenChangeLanguage2(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);

        if (update.getMessage().getText().equals(
                getMessage(Message.UZBEK, getUserLanguage(chatId)))) {
            tgUser.setLanguage(languageRepository.findByName(LanguageName.UZ));
        } else if (update.getMessage().getText().equals(
                getMessage(Message.RUSSIAN, getUserLanguage(chatId)))) {
            tgUser.setLanguage(languageRepository.findByName(LanguageName.RU));
        }
        tgUserRepository.save(tgUser);
        SendMessage sendMessage = new SendMessage(chatId,
                getMessage(Message.LANGUAGE_CHANGED, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forMainMenu(chatId));
        setUserStep(chatId, StepName.CHOOSE_FROM_MAIN_MENU);
        return sendMessage;
    }

    public SendMessage whenOrderLocation(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.SEND_LOCATION, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forSendLocation(chatId));
        setUserStep(chatId, StepName.CHOOSE_PAYMENT);
        return sendMessage;
    }

    public SendMessage whenOrderLocation1(Update update) {
        String chatId = getChatId(update);
        TgUser tgUser = tgUserRepository.findByChatId(chatId);

        if (update.getMessage().hasText()) {
            if (isValidPhoneNumber(update.getMessage().getText())) {
                String phoneNumber = update.getMessage().getText();
                tgUser.setPhoneNumber(phoneNumber);
                tgUserRepository.save(tgUser);
                return executeSendLocation(chatId);
            } else {
                SendMessage sendMessage = new SendMessage(getChatId(update),
                        getMessage(Message.INCORRECT_PHONE_FORMAT, getUserLanguage(chatId)));
                sendMessage.setReplyMarkup(forPhoneNumber(chatId));
                setUserStep(chatId, StepName.INCORRECT_PHONE_FORMAT_1);
                return sendMessage;
            }
        } else {
            String phoneNumber = update.getMessage().getContact().getPhoneNumber();
            phoneNumber = phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber;
            tgUser.setPhoneNumber(phoneNumber);
            tgUserRepository.save(tgUser);
            return executeSendLocation(chatId);
        }
    }

    private SendMessage executeSendLocation(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.SEND_LOCATION, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forSendLocation(chatId));
        setUserStep(chatId, StepName.CHOOSE_PAYMENT);
        return sendMessage;
    }

    private ReplyKeyboardMarkup forSendLocation(String chatId) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();

        button1.setText(getMessage(Message.FOR_LOCATION, getUserLanguage(chatId)));
        button1.setRequestLocation(true);

        row1.add(button1);

        rowList.add(row1);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public SendMessage whenChoosePayment(Update update) {
        String chatId = getChatId(update);
        Location location = update.getMessage().getLocation();
        List<Order> orderList = orderRepository.findAllByUserChatIdOrderByCreatedAtDesc(chatId);
        Order order = orderList.get(0);
        order.setLon(location.getLongitude());
        order.setLat(location.getLatitude());
        orderRepository.save(order);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.CHOOSE_PAYMENT, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forChoosePayment(chatId));
        setUserStep(chatId, StepName.LEAVE_COMMENT);
        return null;
    }

    private ReplyKeyboardMarkup forChoosePayment(String chatId) {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();

        button1.setText(getMessage(Message.CLICK, getUserLanguage(chatId)));
        button2.setText(getMessage(Message.PAYME, getUserLanguage(chatId)));
        button3.setText(getMessage(Message.CASH, getUserLanguage(chatId)));
        row1.add(button1);
        row1.add(button2);
        row2.add(button3);

        rowList.add(row1);
        rowList.add(row2);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public SendMessage whenOrderRegName(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.ENTER_NAME, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        setUserStep(chatId, StepName.ORDER_REGISTER_PHONE);
        return sendMessage;
    }

    public SendMessage whenOrderRegPhone(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.ENTER_PHONE_NUMBER, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forPhoneNumber(chatId));
        setUserStep(chatId, StepName.ORDER_LOCATION);
        return sendMessage;
    }

    public SendMessage whenOrderRegPhone1(Update update) {
        String chatId = getChatId(update);
        String name = update.getMessage().getText();
        TgUser tgUser = tgUserRepository.findByChatId(chatId);
        tgUser.setName(name);
        tgUserRepository.save(tgUser);

        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.ENTER_PHONE_NUMBER, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forPhoneNumber(chatId));
        setUserStep(chatId, StepName.ORDER_LOCATION);
        return sendMessage;
    }

    public SendMessage whenLeaveComment(Update update) {
        String chatId = getChatId(update);
        String text = update.getMessage().getText();
        List<Order> orderList = orderRepository.findAllByUserChatIdOrderByCreatedAtDesc(chatId);
        Order order = orderList.get(0);
        if (text.equals(getMessage(Message.CLICK, getUserLanguage(chatId)))) {
            order.setPaymentProviders(paymentProvidersRepository.findByName(ProviderName.CLICK));
        } else if (text.equals(getMessage(Message.PAYME, getUserLanguage(chatId)))) {
            order.setPaymentProviders(paymentProvidersRepository.findByName(ProviderName.PAYME));
        } else if (text.equals(getMessage(Message.CASH, getUserLanguage(chatId)))) {
            order.setPaymentProviders(paymentProvidersRepository.findByName(ProviderName.CASH));
        }
        Order saved = orderRepository.save(order);
        SendMessage sendMessage = new SendMessage(chatId, getMessage(Message.SEND_COMMENT, getUserLanguage(chatId)));
        sendMessage.setReplyMarkup(forSendComment(update));
        if (saved.getPaymentProviders().getName().equals(ProviderName.CASH)) {
            setUserStep(chatId, StepName.SEND_ORDER_TO_CHANNEL);
        } else {
            setUserStep(chatId, StepName.GO_TO_PAYMENT);
        }
        return sendMessage;
    }

    private ReplyKeyboardMarkup forSendComment(Update update) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();

        button1.setText(getMessage(Message.SKIP_COMMENT, getUserLanguage(getChatId(update))));

        row1.add(button1);

        rowList.add(row1);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public SendMessage whenGoPayment(Update update) {
        String chatId = getChatId(update);
        String text = update.getMessage().getText();
        List<Order> orderList = orderRepository.findAllByUserChatIdOrderByCreatedAtDesc(chatId);
        Order order = orderList.get(0);
        if (!text.equals(getMessage(Message.SKIP_COMMENT, getUserLanguage(chatId)))) {
            order.setComment(text);
            orderRepository.save(order);
        }
        SendMessage sendMessage = new SendMessage(chatId,
                String.format(getMessage(Message.FOR_PAYMENT, getUserLanguage(chatId)), order.getId()));
        sendMessage.setReplyMarkup(forGoPayment(update));
        setUserStep(chatId, StepName.SEND_ORDER_TO_CHANNEL);
        return sendMessage;
    }

    private ReplyKeyboardMarkup forGoPayment(Update update) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();

        button1.setText(getMessage(Message.GO_PAYMENT, getUserLanguage(getChatId(update))));

        row1.add(button1);

        rowList.add(row1);
        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        return markup;
    }


}
