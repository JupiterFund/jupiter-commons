package com.nodeunify.jupiter.commons.mapper.qualifier;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

public class StringQualifier {
    // @formatter:off
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ByteArrayToStringAndTrim { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ParseInt { }
    // @formatter:on

    @ByteArrayToStringAndTrim
    public String byteArrayToStringAndTrim(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, 0, bytes.length, "UTF-8").trim();
    }

    @ParseInt
    public int parseInt(String source) {
        int target = Integer.parseInt(source);
        return target;
    }
}
