package com.bbva.rbvd.lib.r402.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static final String DATE = "yyyy-MM-dd";
    private static final JsonUtil INSTANCE = new JsonUtil();

    private final Gson gson;

    private JsonUtil() {
        gson = new GsonBuilder()
                .setDateFormat(DATE)
                .create();
    }

    public static JsonUtil getInstance() { return INSTANCE; }

    public String serialization(Object o) { return this.gson.toJson(o); }

    public <T> T fromString(String src, Class<T> clazz) { return this.gson.fromJson(src, clazz); }

    public <T> T deserialization(String src, Class<T> clazz) { return this.gson.fromJson(src, clazz); }
}
