package com.nodeunify.jupiter.commons.mapper;

import static org.junit.Assert.assertEquals;

import com.nodeunify.jupiter.commons.dataformat.json.GsonDeser;
import com.nodeunify.jupiter.datastream.v1.StockData;

import org.junit.Before;
import org.junit.Test;

public class GsonDeserTest {

    private GsonDeser gsonDeser;
    private StockData stockData;
    private String stockDataStr;

    @Before
    public void before() {
        gsonDeser = new GsonDeser();
        stockData = StockData.newBuilder().setClosePx(1274).build();
        stockDataStr = "{\"closePx\":\"1274\"}";
    }

    @Test
    public void testToJSON() {
        assertEquals("{\"closePx\":\"1274\"}", gsonDeser.toJson(stockData));
    }

    @Test
    public void testFromJson() {
        StockData quote = gsonDeser.fromJson(stockDataStr, StockData.class);
        assertEquals(1274, quote.getClosePx());
    }
}
