package com.nodeunify.jupiter.commons.dataformat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.nodeunify.jupiter.commons.dataformat.json.adapter.OrderAdapter;
import com.nodeunify.jupiter.commons.dataformat.json.adapter.QuoteAdapter;
import com.nodeunify.jupiter.datastream.v1.Order;
import com.nodeunify.jupiter.datastream.v1.Quote;

public class GsonDeser {

    private Gson gson;

    public GsonDeser() {
        // @formatter:off
        gson = new GsonBuilder()
            .registerTypeAdapter(Quote.class, new QuoteAdapter())
            .registerTypeAdapter(Order.class, new OrderAdapter())
            .create();
        // @formatter:on
    }

    public String toJson(GeneratedMessageV3 object) {
        return gson.toJson(object);
    }

    public <T extends GeneratedMessageV3> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}