package com.nodeunify.jupiter.commons.mapper;

import java.util.Arrays;

import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
import com.gta.qts.c2j.adaptee.structure.SZSEL2_Quotation;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil.ByteArrayToStringAndTrim;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil.ExtractTimestamp;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil.IdentifyMarket;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil.Round;
import com.nodeunify.jupiter.commons.mapper.util.GTAUtil.RoundToInt;
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

// @formatter:off
@Mapper(uses = { GTAUtil.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.WARN)
// @formatter:on
public interface DatastreamMapper {

    DatastreamMapper MAPPER = Mappers.getMapper(DatastreamMapper.class);

    // SSEL2_Quotation -> StockData
    @Mapping(target = "market", expression = "java( com.nodeunify.jupiter.datastream.v1.MarketEnum.Market.SHANGHAI )")
    @Mapping(source = "Symbol", target = "code", qualifiedBy = ByteArrayToStringAndTrim.class)
    @Mapping(source = "Time", target = "timestamp", qualifiedBy = ExtractTimestamp.class)
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
        GTAUtil gtaUtil = new GTAUtil();
        Arrays.stream(data.BuyLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addBidPrice(gtaUtil.round(fieldVal.Price));
                stockData.addBidQty(fieldVal.Volume);
                stockData.addBidNumOrders(fieldVal.TotalOrderNo);
            }
        });
        Arrays.stream(data.SellLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addOfferPrice(gtaUtil.round(fieldVal.Price));
                stockData.addOfferQty(fieldVal.Volume);
                stockData.addOfferNumOrders(fieldVal.TotalOrderNo);
            }
        });
    }

    // SZSEL2_Quotation -> StockData
    @Mapping(target = "market", expression = "java( com.nodeunify.jupiter.datastream.v1.MarketEnum.Market.SHENZHEN )")
    @Mapping(source = "Symbol", target = "code", qualifiedBy = ByteArrayToStringAndTrim.class)
    @Mapping(source = "Time", target = "timestamp", qualifiedBy = ExtractTimestamp.class)
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
        GTAUtil gtaUtil = new GTAUtil();
        Arrays.stream(data.BuyLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addBidPrice(gtaUtil.round(fieldVal.Price));
                stockData.addBidQty(gtaUtil.round(fieldVal.Volume));
                stockData.addBidNumOrders(gtaUtil.mathToIntExact(fieldVal.TotalOrderNo));
            }
        });
        Arrays.stream(data.SellLevel).forEach((fieldVal) -> {
            if (fieldVal != null) {
                stockData.addOfferPrice(gtaUtil.round(fieldVal.Price));
                stockData.addOfferQty(gtaUtil.round(fieldVal.Volume));
                stockData.addOfferNumOrders(gtaUtil.mathToIntExact(fieldVal.TotalOrderNo));
            }
        });
    }

    // TDF_MARKET_DATA -> StockData
    @Mapping(source = "windCode", target = "market", qualifiedBy = IdentifyMarket.class)
    @Mapping(source = "code", target = "code")
    @Mapping(source = "time", target = "timestamp")
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
