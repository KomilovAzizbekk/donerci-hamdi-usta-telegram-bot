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
import uz.mediasolutions.mdeliveryservice.sqlite.SQLiteColumnExtractor;
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
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAECqPpmOzii72KRI-CeBZ73EO3udXhCWwACle0xG0kyUEnGOpf6869NOgEAAwIAA3MAAzUE"));
            tgService.execute(sendPhoto);
        }
    }


    @Scheduled(cron = "0 38 13 8 5 *")
    public void sendNewBotReminder() {
        String dbFilePath = "old_db.sqlite3";
        String tableName = "bot_user";
        String columnName = "user_id";

        // Retrieve the column data
        List<String> oldChatIds = SQLiteColumnExtractor.getColumnData(dbFilePath, tableName, columnName);

        System.out.println("HI");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAECqPlmOzgwyUfgoYobMhxl6ajUwZNcSwACMtwxG9FikEmmhTtwgoUAAU8BAAMCAANzAAM1BA"));
        sendPhoto.setCaption("\uD83C\uDDF7\uD83C\uDDFA Для более удобного пользования мы обновили наш бот. Для начала работы нажмите /start /start /start\n" +
                "\n" +
                "\uD83C\uDDFA\uD83C\uDDFF Foydalanishda qulayroq bo’lishi uchun biz botimizni yangiladik. Boshlash uchun start tugmasini bosing /start /start /start");
        for (String oldChatId : oldChatIds) {
            sendPhoto.setChatId(oldChatId);
            try {
                tgService.execute(sendPhoto);
            } catch (TelegramApiException e) {
                if (e.getMessage().contains("bot was blocked by the user")) {
                    handleBlockedUser(oldChatId); // Specific handling for blocked users
                } else {
                    e.printStackTrace(); // Rethrow the exception to be handled by the caller
                }
            }
        }
    }

    private void handleBlockedUser(String chatId) {
        // Log this info or update your database to mark the user as 'blocked'
        System.out.println("Blocked by user: " + chatId);
    }

}

