package com.merakses.bot.commands;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public interface Command {

    void execute() throws TelegramApiException, IOException;

}
