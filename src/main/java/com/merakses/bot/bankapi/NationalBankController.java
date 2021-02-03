package com.merakses.bot.bankapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.merakses.bot.dto.ExchangePriceDto;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class NationalBankController {

    public static final String BASE_URL = "https://www.nbrb.by/api/exrates/";
    private static final int USD_CUR_ID = 145;

    public Response<ExchangePriceDto> getUsdPrice() throws IOException {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NationalBankApi nationalBankApi = retrofit.create(NationalBankApi.class);

        return nationalBankApi.loadPrice(USD_CUR_ID).execute();
    }
}
