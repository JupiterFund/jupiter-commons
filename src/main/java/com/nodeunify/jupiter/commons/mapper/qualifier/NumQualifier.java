package com.nodeunify.jupiter.commons.mapper.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

public class NumQualifier {
    // @formatter:off
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Round { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface LongToInt { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoundToInt { }

    // @formatter:on

    @Round
    public long round(double source) {
        long target = Math.round(source * 1000);
        return target;
    }

    @LongToInt
    public int longToInt(long source) {
        int target = Math.toIntExact(source);
        return target;
    }

    @RoundToInt
    public int roundToInt(double source) {
        long rounded = round(source);
        int target = longToInt(rounded);
        return target;
    }
}
