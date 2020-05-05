package com.nodeunify.jupiter.commons.dataformat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.nodeunify.jupiter.commons.dataformat.json.adapter.ProtobufAdapter;
import com.nodeunify.jupiter.datastream.v1.FutureData;
import com.nodeunify.jupiter.datastream.v1.IndexData;
import com.nodeunify.jupiter.datastream.v1.OrderQueue;
import com.nodeunify.jupiter.datastream.v1.StockData;
import com.nodeunify.jupiter.datastream.v1.Transaction;
import com.nodeunify.jupiter.trader.ctp.v1.Instrument;
import com.nodeunify.jupiter.trader.ctp.v1.InvestorPosition;
import com.nodeunify.jupiter.trader.ctp.v1.OrderAction;
import com.nodeunify.jupiter.trader.ctp.v1.Trade;
import com.nodeunify.jupiter.trader.ctp.v1.TradingAccount;

public class GsonDeser {

    private Gson gson;

    public GsonDeser() {
        // @formatter:off
        gson = new GsonBuilder()
            .registerTypeAdapter(StockData.class, new ProtobufAdapter(StockData.class))
            .registerTypeAdapter(FutureData.class, new ProtobufAdapter(FutureData.class))
            .registerTypeAdapter(IndexData.class, new ProtobufAdapter(IndexData.class))
            .registerTypeAdapter(com.nodeunify.jupiter.datastream.v1.Order.class, new ProtobufAdapter(com.nodeunify.jupiter.datastream.v1.Order.class))
            .registerTypeAdapter(OrderQueue.class, new ProtobufAdapter(OrderQueue.class))
            .registerTypeAdapter(Transaction.class, new ProtobufAdapter(Transaction.class))
            .registerTypeAdapter(Instrument.class, new ProtobufAdapter(Instrument.class))
            .registerTypeAdapter(InvestorPosition.class, new ProtobufAdapter(InvestorPosition.class))
            .registerTypeAdapter(TradingAccount.class, new ProtobufAdapter(TradingAccount.class))
            .registerTypeAdapter(com.nodeunify.jupiter.trader.ctp.v1.Order.class, new ProtobufAdapter(com.nodeunify.jupiter.trader.ctp.v1.Order.class))
            .registerTypeAdapter(OrderAction.class, new ProtobufAdapter(OrderAction.class))
            .registerTypeAdapter(Trade.class, new ProtobufAdapter(Trade.class))
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
