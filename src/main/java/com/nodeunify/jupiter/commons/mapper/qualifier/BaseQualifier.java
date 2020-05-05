package com.nodeunify.jupiter.commons.mapper.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nodeunify.jupiter.datastream.v1.MarketEnum.Market;

import org.mapstruct.Qualifier;

public class BaseQualifier {
    // @formatter:off
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyMarketByWindCode { }

    // @formatter:on

    @IdentifyMarketByWindCode
    public Market identifyMarketByWindCode(String windCode) {
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
