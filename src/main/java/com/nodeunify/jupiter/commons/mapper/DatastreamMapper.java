package com.nodeunify.jupiter.commons.mapper;

import java.util.Arrays;

import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
import com.gta.qts.c2j.adaptee.structure.SZSEL2_Quotation;
import com.nodeunify.jupiter.commons.mapper.qualifier.BaseQualifier;
import com.nodeunify.jupiter.commons.mapper.qualifier.NumQualifier;
import com.nodeunify.jupiter.commons.mapper.qualifier.StringQualifier;
import com.nodeunify.jupiter.commons.mapper.qualifier.BaseQualifier.IdentifyMarketByWindCode;
import com.nodeunify.jupiter.commons.mapper.qualifier.NumQualifier.Round;
import com.nodeunify.jupiter.commons.mapper.qualifier.NumQualifier.RoundToInt;
import com.nodeunify.jupiter.commons.mapper.qualifier.StringQualifier.ByteArrayToStringAndTrim;
import com.nodeunify.jupiter.commons.mapper.qualifier.StringQualifier.ParseInt;
import com.nodeunify.jupiter.datastream.v1.FutureData;
import com.nodeunify.jupiter.datastream.v1.Order;
import com.nodeunify.jupiter.datastream.v1.StockData;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cn.com.wind.td.tdf.TDF_MARKET_DATA;
import cn.com.wind.td.tdf.TDF_ORDER;
import ctp.thostmduserapi.CThostFtdcDepthMarketDataField;

// @formatter:off
@Mapper(uses = { BaseQualifier.class, StringQualifier.class, NumQualifier.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.WARN)
// @formatter:on
public interface DatastreamMapper {

    DatastreamMapper MAPPER = Mappers.getMapper(DatastreamMapper.class);

    // TODO: check the returned timestamp from GTA
    // SSEL2_Quotation -> StockData
    @Mapping(target = "market", expression = "java( com.nodeunify.jupiter.datastream.v1.MarketEnum.Market.SHANGHAI )")
    @Mapping(source = "Symbol", target = "code", qualifiedBy = ByteArrayToStringAndTrim.class)
    @Mapping(target = "time", expression = "java(com.nodeunify.jupiter.commons.util.TimeUtil.extractTime(data.Time))")
    @Mapping(source = "PreClosePrice", target = "preClosePx", qualifiedBy = Round.class)
    @Mapping(source = "OpenPrice", target = "openPx", qualifiedBy = Round.class)
    @Mapping(source = "ClosePrice", target = "closePx", qualifiedBy = Round.class)
    @Mapping(source = "HighPrice", target = "highPx", qualifiedBy = Round.class)
    @Mapping(source = "LowPrice", target = "lowPx", qualifiedBy = Round.class)
    @Mapping(source = "LastPrice", target = "lastPx", qualifiedBy = Round.class)
    @Mapping(source = "TotalNo", target = "numTrades")
    @Mapping(source = "TotalVolume", target = "totalVolumeTrade", qualifiedBy = Round.class)
    @Mapping(source = "TotalAmount", target = "totalValueTrade", qualifiedBy = Round.class)
    @Mapping(source = "TotalBuyOrderVolume", target = "totalBidQty")
    @Mapping(source = "TotalSellOrderVolume", target = "totalOfferQty")
    @Mapping(source = "WtAvgBuyPrice", target = "weightedAvgBidPx", qualifiedBy = Round.class)
    @Mapping(source = "WtAvgSellPrice", target = "weightedAvgOfferPx", qualifiedBy = Round.class)
    @Mapping(source = "YTM", target = "yieldToMaturity", qualifiedBy = RoundToInt.class)
    @Mapping(source = "ETFBuyVolume", target = "etfBuyAmount")
    @Mapping(source = "ETFBuyAmount", target = "etfBuyMoney", qualifiedBy = Round.class)
    @Mapping(source = "ETFBuyNo", target = "etfBuyNumber")
    @Mapping(source = "ETFSellVolume", target = "etfSellAmount")
    @Mapping(source = "ETFSellAmount", target = "etfSellMoney", qualifiedBy = Round.class)
    @Mapping(source = "ETFSellNo", target = "etfSellNumber")
    @Mapping(source = "WithdrawBuyAmount", target = "withdrawBuyMoney", qualifiedBy = Round.class)
    @Mapping(source = "WithdrawBuyVolume", target = "withdrawBuyAmount")
    @Mapping(source = "WithdrawBuyNo", target = "withdrawBuyNumber")
    @Mapping(source = "WithdrawSellAmount", target = "withdrawSellMoney", qualifiedBy = Round.class)
    @Mapping(source = "WithdrawSellVolume", target = "withdrawSellAmount")
    @Mapping(source = "WithdrawSellNo", target = "withdrawSellNumber")
    @Mapping(source = "IOPV", target = "IOPV", qualifiedBy = Round.class)
    StockData map(SSEL2_Quotation data);

    @AfterMapping
    default void afterMapping(SSEL2_Quotation data, @MappingTarget StockData.Builder stockData) {
        NumQualifier qualifier = new NumQualifier();
        Arrays.stream(data.BuyLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addBidPrice(qualifier.round(fieldVal.Price));
                stockData.addBidQty(fieldVal.Volume);
                stockData.addBidNumOrders(fieldVal.TotalOrderNo);
            }
        });
        Arrays.stream(data.SellLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addOfferPrice(qualifier.round(fieldVal.Price));
                stockData.addOfferQty(fieldVal.Volume);
                stockData.addOfferNumOrders(fieldVal.TotalOrderNo);
            }
        });
    }

    // SZSEL2_Quotation -> StockData
    @Mapping(target = "market", expression = "java( com.nodeunify.jupiter.datastream.v1.MarketEnum.Market.SHENZHEN )")
    @Mapping(source = "Symbol", target = "code", qualifiedBy = ByteArrayToStringAndTrim.class)
    @Mapping(target = "time", expression = "java(com.nodeunify.jupiter.commons.util.TimeUtil.extractTime(data.Time))")
    @Mapping(source = "PreClosePrice", target = "preClosePx", qualifiedBy = Round.class)
    @Mapping(source = "OpenPrice", target = "openPx", qualifiedBy = Round.class)
    @Mapping(source = "ClosePrice", target = "closePx", qualifiedBy = Round.class)
    @Mapping(source = "HighPrice", target = "highPx", qualifiedBy = Round.class)
    @Mapping(source = "LowPrice", target = "lowPx", qualifiedBy = Round.class)
    @Mapping(source = "LastPrice", target = "lastPx", qualifiedBy = Round.class)
    @Mapping(source = "TotalNo", target = "numTrades")
    @Mapping(source = "TotalVolume", target = "totalVolumeTrade", qualifiedBy = Round.class)
    @Mapping(source = "TotalAmount", target = "totalValueTrade", qualifiedBy = Round.class)
    @Mapping(source = "TotalBuyOrderVolume", target = "totalBidQty", qualifiedBy = Round.class)
    @Mapping(source = "TotalSellOrderVolume", target = "totalOfferQty", qualifiedBy = Round.class)
    @Mapping(source = "WtAvgBuyPrice", target = "weightedAvgBidPx", qualifiedBy = Round.class)
    @Mapping(source = "WtAvgSellPrice", target = "weightedAvgOfferPx", qualifiedBy = Round.class)
    @Mapping(source = "IOPV", target = "IOPV", qualifiedBy = Round.class)
    @Mapping(source = "PriceUpLimit", target = "priceUpLimit", qualifiedBy = Round.class)
    @Mapping(source = "PriceDownLimit", target = "priceDownLimit", qualifiedBy = Round.class)
    @Mapping(source = "PERatio1", target = "peRatio1", qualifiedBy = RoundToInt.class)
    @Mapping(source = "PERatio2", target = "peRatio2", qualifiedBy = RoundToInt.class)
    StockData map(SZSEL2_Quotation data);

    @AfterMapping
    default void afterMapping(SZSEL2_Quotation data, @MappingTarget StockData.Builder stockData) {
        NumQualifier qualifier = new NumQualifier();
        Arrays.stream(data.BuyLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addBidPrice(qualifier.round(fieldVal.Price));
                stockData.addBidQty(qualifier.round(fieldVal.Volume));
                stockData.addBidNumOrders(qualifier.longToInt(fieldVal.TotalOrderNo));
            }
        });
        Arrays.stream(data.SellLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addOfferPrice(qualifier.round(fieldVal.Price));
                stockData.addOfferQty(qualifier.round(fieldVal.Volume));
                stockData.addOfferNumOrders(qualifier.longToInt(fieldVal.TotalOrderNo));
            }
        });
    }

    // TDF_MARKET_DATA -> StockData
    @Mapping(source = "windCode", target = "market", qualifiedBy = IdentifyMarketByWindCode.class)
    @Mapping(source = "code", target = "code")
    @Mapping(source = "actionDay", target = "date")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "tradingDay", target = "tradeDate")
    @Mapping(source = "preClose", target = "preClosePx")
    @Mapping(source = "open", target = "openPx")
    @Mapping(source = "high", target = "highPx")
    @Mapping(source = "low", target = "lowPx")
    @Mapping(source = "match", target = "lastPx")
    @Mapping(source = "numTrades", target = "numTrades")
    @Mapping(source = "volume", target = "totalVolumeTrade")
    @Mapping(source = "turnover", target = "totalValueTrade")
    @Mapping(source = "totalBidVol", target = "totalBidQty")
    @Mapping(source = "totalAskVol", target = "totalOfferQty")
    @Mapping(source = "weightedAvgBidPrice", target = "weightedAvgBidPx")
    @Mapping(source = "weightedAvgAskPrice", target = "weightedAvgOfferPx")
    @Mapping(source = "IOPV", target = "IOPV")
    @Mapping(source = "highLimited", target = "priceUpLimit")
    @Mapping(source = "lowLimited", target = "priceDownLimit")
    @Mapping(source = "syl1", target = "peRatio1")
    @Mapping(source = "syl2", target = "peRatio2")
    @Mapping(source = "yieldToMaturity", target = "yieldToMaturity")
    StockData map(TDF_MARKET_DATA data);

    // CThostFtdcDepthMarketDataField -> FutureData
    @Mapping(source = "instrumentID", target = "code")
    @Mapping(source = "actionDay", target = "date", qualifiedBy = ParseInt.class)
    @Mapping(target = "time", expression = "java(com.nodeunify.jupiter.commons.util.TimeUtil.buildTime(data.getUpdateTime(), data.getUpdateMillisec()))")
    @Mapping(source = "tradingDay", target = "tradeDate", qualifiedBy = ParseInt.class)
    @Mapping(source = "preClosePrice", target = "preClosePx", qualifiedBy = Round.class)
    @Mapping(source = "openPrice", target = "openPx", qualifiedBy = Round.class)
    @Mapping(source = "closePrice", target = "closePx", qualifiedBy = Round.class)
    @Mapping(source = "highestPrice", target = "highPx", qualifiedBy = Round.class)
    @Mapping(source = "lowestPrice", target = "lowPx", qualifiedBy = Round.class)
    @Mapping(source = "lastPrice", target = "lastPx", qualifiedBy = Round.class)
    @Mapping(source = "volume", target = "totalVolumeTrade", qualifiedBy = Round.class)
    @Mapping(source = "turnover", target = "totalValueTrade", qualifiedBy = Round.class)
    @Mapping(source = "preOpenInterest", target = "preOpenInterest", qualifiedBy = Round.class)
    @Mapping(source = "openInterest", target = "openInterest", qualifiedBy = Round.class)
    @Mapping(source = "preSettlementPrice", target = "preSettlePrice", qualifiedBy = Round.class)
    @Mapping(source = "settlementPrice", target = "settlePrice", qualifiedBy = Round.class)
    @Mapping(source = "upperLimitPrice", target = "priceUpLimit", qualifiedBy = Round.class)
    @Mapping(source = "lowerLimitPrice", target = "priceDownLimit", qualifiedBy = Round.class)
    @Mapping(source = "preDelta", target = "preDelta", qualifiedBy = RoundToInt.class)
    @Mapping(source = "currDelta", target = "currDelta", qualifiedBy = RoundToInt.class)
    FutureData map(CThostFtdcDepthMarketDataField data);

    @AfterMapping
    default void afterMapping(CThostFtdcDepthMarketDataField data, @MappingTarget FutureData.Builder futureData) {
        NumQualifier qualifier = new NumQualifier();
        futureData
            .addBidPrice(qualifier.round(data.getBidPrice1()))
            .addBidPrice(qualifier.round(data.getBidPrice2()))
            .addBidPrice(qualifier.round(data.getBidPrice3()))
            .addBidPrice(qualifier.round(data.getBidPrice4()))
            .addBidPrice(qualifier.round(data.getBidPrice5()))
            .addBidQty(qualifier.round(data.getBidVolume1()))
            .addBidQty(qualifier.round(data.getBidVolume2()))
            .addBidQty(qualifier.round(data.getBidVolume3()))
            .addBidQty(qualifier.round(data.getBidVolume4()))
            .addBidQty(qualifier.round(data.getBidVolume5()))
            .addOfferPrice(qualifier.round(data.getAskPrice1()))
            .addOfferPrice(qualifier.round(data.getAskPrice2()))
            .addOfferPrice(qualifier.round(data.getAskPrice3()))
            .addOfferPrice(qualifier.round(data.getAskPrice4()))
            .addOfferPrice(qualifier.round(data.getAskPrice5()))
            .addOfferQty(qualifier.round(data.getAskVolume1()))
            .addOfferQty(qualifier.round(data.getAskVolume2()))
            .addOfferQty(qualifier.round(data.getAskVolume3()))
            .addOfferQty(qualifier.round(data.getAskVolume4()))
            .addOfferQty(qualifier.round(data.getAskVolume5()));
    }

    // TDF_ORDER -> Order
    @Mapping(source = "code", target = "code")
    Order map(TDF_ORDER data);

    // TODO: demo code to be deleted
    // @Named("mapPriceToBidPrice")
    // default List<Long> mapPriceToBidPrice(BuySellLevelInfo3[] buyLevel) {
    // return Arrays.stream(buyLevel).map(fieldVal -> Math.round(fieldVal.Price *
    // 1000))
    // .collect(Collectors.<Long>toList());
    // }

}
