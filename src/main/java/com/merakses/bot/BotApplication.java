package com.merakses.bot;

import com.merakses.bot.config.BotProperties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotApplication {

    public static void main(String[] args) {
        try {
            initBot();
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void initBot() throws TelegramApiException {
            BotProperties botProperties = new BotProperties();

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new ExchangeBot(botProperties));
    }
}
