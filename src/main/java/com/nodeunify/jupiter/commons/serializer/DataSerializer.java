package com.nodeunify.jupiter.commons.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.nodeunify.jupiter.commons.serializer.QuoteAdapter;
import com.nodeunify.jupiter.datastream.v1.Order;
import com.nodeunify.jupiter.datastream.v1.Quote;

public class DataSerializer {

    private Gson gson;

    public DataSerializer() {
        // @formatter:off
        gson = new GsonBuilder()
            .registerTypeAdapter(Quote.class, new QuoteAdapter())
            .registerTypeAdapter(Order.class, new OrderAdapter())
            .create();
        // @formatter:on
    }

    public String toJson(GeneratedMessageV3 message) {
        return gson.toJson(message);
    }
}
