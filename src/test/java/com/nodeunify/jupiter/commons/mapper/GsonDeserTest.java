package com.nodeunify.jupiter.commons.mapper;

import static org.junit.Assert.assertEquals;

import com.nodeunify.jupiter.commons.dataformat.json.GsonDeser;
import com.nodeunify.jupiter.datastream.v1.Quote;

import org.junit.Before;
import org.junit.Test;

public class GsonDeserTest {

    private GsonDeser gsonDeser;
    private Quote quote;

    @Before
    public void before() {
        gsonDeser = new GsonDeser();
        quote = Quote.newBuilder().build();
    }

    @Test
    public void testToJSON() {
        assertEquals("{}", gsonDeser.toJson(quote));
    }
}
