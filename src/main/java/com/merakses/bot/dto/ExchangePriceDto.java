package com.merakses.bot.dto;

import com.google.gson.annotations.SerializedName;

public class ExchangePriceDto {

    @SerializedName("Cur_OfficialRate")
    private double exchangePrice;

    public double getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(double exchangePrice) {
        this.exchangePrice = exchangePrice;
    }
}
