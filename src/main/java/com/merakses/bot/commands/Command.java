package com.merakses.bot.commands;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {

    void execute() throws TelegramApiException;

}
