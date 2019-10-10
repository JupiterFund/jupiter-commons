package com.nodeunify.jupiter.commons.mapper.util;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nodeunify.jupiter.datastream.v1.MarketEnum.Market;

import org.mapstruct.Qualifier;

public class GTAUtil {
    // @formatter:off
    
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Round { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MathToIntExact { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoundToInt { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ByteArrayToStringAndTrim { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtractTimestamp { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyMarket { }

    // @formatter:on

    @Round
    public long round(double in) {
        return Math.round(in * 1000);
    }

    @MathToIntExact
    public int mathToIntExact(long in) {
        return Math.toIntExact(in);
    }

    @RoundToInt
    public int roundToInt(double in) {
        return mathToIntExact(round(in));
    }

    @ByteArrayToStringAndTrim
    public String byteArrayToStringAndTrim(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, 0, bytes.length, "UTF-8").trim();
    }

    @ExtractTimestamp
    public long extractTimestamp(int time) {
        // TODO: check format of time field from GTA
        return time % 1000000000;
    }

    @IdentifyMarket
    public Market identifyMarket(String windCode) {
        if (windCode.endsWith("sh")) {
            return Market.SHANGHAI;
        }
        if (windCode.endsWith("sz")) {
            return Market.SHENZHEN;
        }
        if (windCode.endsWith("hk")) {
            return Market.HONGKONG;
        }
        return Market.SHANGHAI;
    }
}
