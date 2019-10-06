package com.nodeunify.jupiter.commons.mapper;

import static org.junit.Assert.assertEquals;

import com.nodeunify.jupiter.commons.dataformat.json.GsonDeser;
import com.nodeunify.jupiter.datastream.v1.Quote;

import org.junit.Before;
import org.junit.Test;

public class GsonDeserTest {

    private GsonDeser gsonDeser;
    private Quote quote;
    private String quoteStr;

    @Before
    public void before() {
        gsonDeser = new GsonDeser();
        quote = Quote.newBuilder().setClosePx(1274).build();
        quoteStr = "{\"closePx\":\"1274\"}";
    }

    @Test
    public void testToJSON() {
        assertEquals("{\"closePx\":\"1274\"}", gsonDeser.toJson(quote));
    }

    @Test
    public void testFromJson() {
        Quote quote = gsonDeser.fromJson(quoteStr, Quote.class);
        assertEquals(1274, quote.getClosePx());
    }
}
