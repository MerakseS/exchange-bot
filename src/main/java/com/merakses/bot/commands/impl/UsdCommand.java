package com.merakses.bot.commands.impl;

import com.merakses.bot.bankapi.NationalBankController;
import com.merakses.bot.commands.Command;
import com.merakses.bot.dto.ExchangePriceDto;
import com.merakses.bot.exception.ExchangeBotException;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import retrofit2.Response;

import java.io.IOException;

public class UsdCommand implements Command {

    private final DefaultAbsSender bot;
    private final Long chatId;

    public UsdCommand(DefaultAbsSender bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void execute() throws TelegramApiException, IOException {
        NationalBankController bankController = new NationalBankController();
        Response<ExchangePriceDto> response = bankController.getUsdPrice();

        if (!response.isSuccessful() || response.body() == null) {
            throw new ExchangeBotException("Can't get usd exchange rate.");
        }

        double price = response.body().getExchangePrice();

        SendMessage sendMessage = new SendMessage(chatId.toString(), String.format("1 USD = %.3f BYN", price));
        bot.execute(sendMessage);
    }
}
