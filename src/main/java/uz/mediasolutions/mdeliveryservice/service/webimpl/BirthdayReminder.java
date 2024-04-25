package uz.mediasolutions.mdeliveryservice.service.webimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.utills.constants.Message;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BirthdayReminder {

    private final TgUserRepository tgUserRepository;
    private final TgService tgService;
    private final MakeService makeService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendBirthdayCelebration() throws TelegramApiException {
        System.out.println("HI");
        LocalDate today = LocalDate.now();
        String day;
        String month;
        int dayOfMonth = today.getDayOfMonth();
        int monthValue = today.getMonthValue();
        day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        month = monthValue < 10 ? "0" + monthValue : String.valueOf(monthValue);

        String birthday = day + "." + month;
        String birthday2 = dayOfMonth + "." + month;
        List<TgUser> users = tgUserRepository.findAllByBirthdayStartingWithOrBirthdayStartingWith(birthday, birthday2);

        SendPhoto sendPhoto = new SendPhoto();
        for (TgUser user : users) {
            sendPhoto.setChatId(user.getChatId());
            sendPhoto.setCaption(
                    String.format(makeService.getMessage(Message.CELEBRATION,
                            makeService.getUserLanguage(user.getChatId())), user.getName()));
            sendPhoto.setParseMode("HTML");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIfkGYqEfGHcC-7VDnZOG1U98vsMD6_AAKV7TEbSTJQSeucf-OA7xpoAQADAgADcwADNAQ"));
            tgService.execute(sendPhoto);
        }
    }

}
