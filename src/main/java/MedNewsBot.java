import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MedNewsBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage sendMessage = new SendMessage(chatId, text);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "superenlightened_bot";
    }

    public String getBotToken() {
        return "922921631:AAGN9O2LDJ8TWvKrrybJe8dHmJeEB8aQdYU";
    }


    public static void main(String[] args) {

        /*System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "ru-new.tgproxy.today");
        System.getProperties().put( "socksProxyPort", "443");*/

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        MedNewsBot myBot = new MedNewsBot();

        /*String proxyHost = "ru-new.tgproxy.today";
        int proxyPort = 443;
        int timeout = 20 * 1000;
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(new HttpHost(proxyHost, proxyPort))
                        .setSocketTimeout(timeout)
                        .setConnectionRequestTimeout(timeout)
                        .setConnectTimeout(timeout)
                        .build();
        myBot.getOptions().setRequestConfig(requestConfig);*/

        try {
            telegramBotsApi.registerBot(myBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
