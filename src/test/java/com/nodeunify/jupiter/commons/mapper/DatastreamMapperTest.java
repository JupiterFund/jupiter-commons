package com.nodeunify.jupiter.commons.mapper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.gta.qts.c2j.adaptee.structure.BuySellLevelInfo3;
import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
import com.nodeunify.jupiter.datastream.v1.Quote;

public class DatastreamMapperTest {
    private static SSEL2_Quotation quotation;

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
    }

    @Test
    public void testSSEL2_QuotationToQuote() {
        Quote quote = DatastreamMapper.MAPPER.map(quotation);
        assertEquals(143025, quote.getTimestamp());
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
}
