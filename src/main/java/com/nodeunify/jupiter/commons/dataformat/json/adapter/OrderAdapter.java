package com.nodeunify.jupiter.commons.dataformat.json.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nodeunify.jupiter.datastream.v1.Order;

public class OrderAdapter implements JsonSerializer<Order>, JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement serialize(Order src, Type typeOfSrc, JsonSerializationContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
