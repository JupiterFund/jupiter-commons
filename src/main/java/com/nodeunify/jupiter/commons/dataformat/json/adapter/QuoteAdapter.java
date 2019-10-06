package com.nodeunify.jupiter.commons.dataformat.json.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nodeunify.jupiter.datastream.v1.Quote;

// Example Adapter
// TODO: to be deleted
public class QuoteAdapter implements JsonSerializer<Quote>, JsonDeserializer<Quote> {

    @Override
    public Quote deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement serialize(Quote src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", src.getCode());
        jsonObject.addProperty("timestamp", src.getTimestamp());
        jsonObject.addProperty("preClosePx", src.getPreClosePx());
        jsonObject.addProperty("openPx", src.getOpenPx());
        jsonObject.addProperty("closePx", src.getClosePx());
        jsonObject.addProperty("highPx", src.getHighPx());
        jsonObject.addProperty("lowPx", src.getLowPx());
        jsonObject.addProperty("lastPx", src.getLastPx());
        jsonObject.addProperty("numTrades", src.getNumTrades());
        jsonObject.addProperty("totalVolumeTrade", src.getTotalVolumeTrade());
        jsonObject.addProperty("totalValueTrade", src.getTotalValueTrade());
        jsonObject.addProperty("totalBidNumber", src.getTotalBidNumber());
        jsonObject.addProperty("totalBidQty", src.getTotalBidQty());
        jsonObject.addProperty("totalOfferNumber", src.getTotalOfferNumber());
        jsonObject.addProperty("totalOfferQty", src.getTotalOfferQty());
        jsonObject.addProperty("weightedAvgBidPx", src.getWeightedAvgBidPx());
        jsonObject.addProperty("weightedAvgOfferPx", src.getWeightedAvgOfferPx());
        jsonObject.addProperty("altWeightedAvgBidPx", src.getAltWeightedAvgBidPx());
        jsonObject.addProperty("altWeightedAvgOfferPx", src.getAltWeightedAvgOfferPx());
        jsonObject.addProperty("warLowerPx", src.getWarLowerPx());
        jsonObject.addProperty("warUpperPx", src.getWarUpperPx());
        jsonObject.addProperty("withdrawBuyAmount", src.getWithdrawBuyAmount());
        jsonObject.addProperty("withdrawBuyMoney", src.getWithdrawBuyMoney());
        jsonObject.addProperty("withdrawBuyNumber", src.getWithdrawBuyNumber());
        jsonObject.addProperty("withdrawSellAmount", src.getWithdrawSellAmount());
        jsonObject.addProperty("withdrawSellMoney", src.getWithdrawSellMoney());
        jsonObject.addProperty("withdrawSellNumber", src.getWithdrawSellNumber());
        jsonObject.addProperty("etfBuyAmount", src.getEtfBuyAmount());
        jsonObject.addProperty("etfBuyMoney", src.getEtfBuyMoney());
        jsonObject.addProperty("etfBuyNumber", src.getEtfBuyNumber());
        jsonObject.addProperty("etfSellAmount", src.getEtfSellAmount());
        jsonObject.addProperty("etfSellMoney", src.getEtfSellMoney());
        jsonObject.addProperty("etfSellNumber", src.getEtfSellNumber());
        jsonObject.addProperty("iopv", src.getIOPV());
        jsonObject.addProperty("yieldToMaturity", src.getYieldToMaturity());
        JsonArray bidPrice = new JsonArray();
        src.getBidPriceList().stream().forEach(price -> bidPrice.add(price));
        jsonObject.add("bidPrice", bidPrice);
        JsonArray bidOrderQty = new JsonArray();
        src.getBidOrderQtyList().stream().forEach(orderQty -> bidOrderQty.add(orderQty));
        jsonObject.add("bidOrderQty", bidOrderQty);
        JsonArray bidNumOrders = new JsonArray();
        src.getBidNumOrdersList().stream().forEach(numOrders -> bidNumOrders.add(numOrders));
        jsonObject.add("bidNumOrders", bidNumOrders);
        JsonArray offerPrice = new JsonArray();
        src.getOfferPriceList().stream().forEach(price -> offerPrice.add(price));
        jsonObject.add("offerPrice", offerPrice);
        JsonArray offerOrderQty = new JsonArray();
        src.getOfferOrderQtyList().stream().forEach(orderQty -> offerOrderQty.add(orderQty));
        jsonObject.add("offerOrderQty", offerOrderQty);
        JsonArray offerNumOrders = new JsonArray();
        src.getOfferNumOrdersList().stream().forEach(numOrders -> offerNumOrders.add(numOrders));
        jsonObject.add("offerNumOrders", offerNumOrders);
        jsonObject.addProperty("priceUpLimit", src.getPriceUpLimit());
        jsonObject.addProperty("priceDownLimit", src.getPriceDownLimit());
        jsonObject.addProperty("peRatio1", src.getPeRatio1());
        jsonObject.addProperty("peRatio2", src.getPeRatio2());
        jsonObject.addProperty("priceUpDown1", src.getPriceUpDown1());
        jsonObject.addProperty("priceUpDown2", src.getPriceUpDown2());
        return jsonObject;
    }

}
