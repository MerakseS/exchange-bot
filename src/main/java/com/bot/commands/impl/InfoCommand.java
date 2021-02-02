package com.bot.commands.impl;

import com.bot.commands.Command;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InfoCommand implements Command {
    private static final String INFO = """
            This is a bot for viewing the current exchange rate of foreign currency against the Belarusian ruble.

            Available commands:
            /info - information about the bot,
            /usd - US dollar rate.
            """;


    private final DefaultAbsSender bot;
    private final Long chatId;

    public InfoCommand(DefaultAbsSender bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void execute() throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(chatId.toString(), INFO);
        bot.execute(sendMessage);
    }
}
