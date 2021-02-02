package com.bot;

import com.bot.commands.Command;
import com.bot.commands.impl.InfoCommand;
import com.bot.commands.impl.StartCommand;
import com.bot.commands.impl.UsdCommand;
import com.bot.config.BotProperties;
import com.bot.exception.ExchangeBotException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;

public class ExchangeBot extends TelegramLongPollingBot {
    public static final String START_COMMAND = "/start";
    public static final String INFO_COMMAND = "/info";
    public static final String USD_COMMAND = "/usd";

    private final BotProperties botProperties;

    public ExchangeBot(BotProperties botProperties) {
        this.botProperties = botProperties;
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                handleMessage(update.getMessage());
            }
        } catch (Exception exception) {
            handleException(exception, update.getMessage());
        }
    }

    private void handleMessage(Message message) throws Exception {
        String messageText = message.getText().toLowerCase();
        Command command;

        switch (messageText) {
            case START_COMMAND:
            case INFO_COMMAND:
                command = new InfoCommand(this, message.getChatId());
                break;
            case USD_COMMAND:
                command = new UsdCommand();
                break;
            default:
                return;
        }
        command.execute();
    }

    private void handleException(Exception exception, Message message) {
        try {
            String errorMessage;
            if (exception instanceof ExchangeBotException) {
                errorMessage = exception.getMessage();
            } else {
                errorMessage = "Critical error! Code: " + UUID.randomUUID().toString();
            }

            SendMessage sendMessage = new SendMessage(message.getChatId().toString(), errorMessage);
            sendMessage.setReplyToMessageId(message.getMessageId());
            this.execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
