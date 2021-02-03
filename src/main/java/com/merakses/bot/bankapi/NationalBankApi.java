package com.merakses.bot.bankapi;

import com.merakses.bot.dto.ExchangePriceDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NationalBankApi {

    @GET("rates/{cur_id}")
    Call<ExchangePriceDto> loadPrice(@Path("cur_id") int currencyId);
}
