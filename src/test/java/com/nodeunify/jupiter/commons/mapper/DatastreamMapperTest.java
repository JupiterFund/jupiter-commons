package com.nodeunify.jupiter.commons.mapper;

import org.junit.Before;
import org.junit.Test;

import cn.com.wind.td.tdf.TDF_MARKET_DATA;
// import ctp.thostmduserapi.CThostFtdcDepthMarketDataField;

import static org.junit.Assert.*;

import com.gta.qts.c2j.adaptee.structure.BuySellLevelInfo3;
import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
// import com.nodeunify.jupiter.datastream.v1.FutureData;
import com.nodeunify.jupiter.datastream.v1.StockData;
import com.nodeunify.jupiter.datastream.v1.MarketEnum.Market;

public class DatastreamMapperTest {
    private static SSEL2_Quotation quotation;
    private static TDF_MARKET_DATA marketData;
    // private static CtpMarketData ctpMarketData;

    @Before
    public void before() {
        quotation = new SSEL2_Quotation();
        quotation.Time = 143025;
        quotation.Symbol = "600100".getBytes();
        quotation.PreClosePrice = 13.23;
        quotation.OpenPrice = 14.55;
        quotation.ClosePrice = 15.32;
        quotation.HighPrice = 15.82;
        quotation.LowPrice = 14.23;
        quotation.TotalNo = 333022;
        quotation.TotalVolume = 10102045;
        quotation.TotalAmount = 3532.45;
        BuySellLevelInfo3 buyLevel = new BuySellLevelInfo3();
        buyLevel.TotalOrderNo = 1323;
        buyLevel.Volume = 343434;
        buyLevel.Price = 15.22;
        quotation.BuyLevel[0] = buyLevel;

        marketData = new TDF_MARKET_DATA();
        marketData.setWindCode("100200.sz");
        marketData.setCode("100200");
        marketData.setOpen(12330);
        marketData.setActionDay(20190120);
        marketData.setTradingDay(20190121);

        // ctpMarketData = new CtpMarketData();
        // ctpMarketData.setPreClosePrice(10.23);
        // ctpMarketData.setOpenPrice(10.11);
    }

    @Test
    public void testSSEL2_QuotationToStockData() {
        StockData stockData = DatastreamMapper.MAPPER.map(quotation);
        // assertEquals(143025, stockData.getTimestamp());
        assertEquals(Market.SHANGHAI, stockData.getMarket());
        assertEquals("600100", stockData.getCode());
        assertEquals(13230, stockData.getPreClosePx());
        assertEquals(14550, stockData.getOpenPx());
        assertEquals(15320, stockData.getClosePx());
        assertEquals(15820, stockData.getHighPx());
        assertEquals(14230, stockData.getLowPx());
        assertNotNull(stockData.getBidQtyList());
        assertNotNull(stockData.getBidQtyList().get(0));
        assertEquals(343434, stockData.getBidQtyList().get(0).longValue());
    }

    @Test
    public void testTDF_MARKET_DATAToStockData() {
        StockData stockData = DatastreamMapper.MAPPER.map(marketData);
        assertEquals(Market.SHENZHEN, stockData.getMarket());
        assertEquals(1, stockData.getMarketValue());
        assertEquals(20190120, stockData.getDate());
        assertEquals(12330, stockData.getOpenPx());
    }

    // Unit test: unsuccessful
    // Error: java.lang.UnsatisfiedLinkError: ctp.thostmduserapi.thostmduserapiJNI.swig_module_init()V
    // Reason: dll or so files must be added into library path
    // @Test
    // public void testCThostFtdcDepthMarketDataFieldToFutureData() {
    //     FutureData futureData = DatastreamMapper.MAPPER.map(ctpMarketData);
    //     assertEquals(10230, futureData.getPreClosePx());
    //     assertEquals(10110, futureData.getOpenPx());
    // }

    // class CtpMarketData extends CThostFtdcDepthMarketDataField {
    // }
}
