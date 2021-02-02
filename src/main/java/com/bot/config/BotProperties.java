package com.bot.config;

public class BotProperties {
    public static final String BOT_USERNAME_VARIABLE = "BOT_USERNAME";
    public static final String BOT_TOKEN_VARIABLE = "BOT_TOKEN";

    private final String username;
    private final String token;

    public BotProperties() {
        String username = System.getenv(BOT_USERNAME_VARIABLE);
        String token = System.getenv(BOT_TOKEN_VARIABLE);

        if (username == null) {
            throw new IllegalStateException("Bot username is empty!");
        }

        if (token == null) {
            throw new IllegalStateException("Bot token is empty!");
        }

        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
