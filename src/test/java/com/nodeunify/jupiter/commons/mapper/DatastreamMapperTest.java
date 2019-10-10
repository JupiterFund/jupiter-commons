package com.nodeunify.jupiter.commons.mapper;

import org.junit.Before;
import org.junit.Test;

import cn.com.wind.td.tdf.TDF_MARKET_DATA;

import static org.junit.Assert.*;

import com.gta.qts.c2j.adaptee.structure.BuySellLevelInfo3;
import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
import com.nodeunify.jupiter.datastream.v1.Quote;
import com.nodeunify.jupiter.datastream.v1.MarketEnum.Market;

public class DatastreamMapperTest {
    private static SSEL2_Quotation quotation;
    private static TDF_MARKET_DATA marketData;

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
    }

    @Test
    public void testSSEL2_QuotationToQuote() {
        Quote quote = DatastreamMapper.MAPPER.map(quotation);
        assertEquals(143025, quote.getTimestamp());
        assertEquals(Market.SHANGHAI, quote.getMarket());
        assertEquals("600100", quote.getCode());
        assertEquals(13230, quote.getPreClosePx());
        assertEquals(14550, quote.getOpenPx());
        assertEquals(15320, quote.getClosePx());
        assertEquals(15820, quote.getHighPx());
        assertEquals(14230, quote.getLowPx());
        assertNotNull(quote.getBidOrderQtyList());
        assertNotNull(quote.getBidOrderQtyList().get(0));
        assertEquals(343434, quote.getBidOrderQtyList().get(0).longValue());
    }

    @Test
    public void testTDF_MARKET_DATAToQuote() {
        Quote quote = DatastreamMapper.MAPPER.map(marketData);
        assertEquals(Market.SHENZHEN, quote.getMarket());
        assertEquals(1, quote.getMarketValue());
        assertEquals(12330, quote.getOpenPx());
    }
}
