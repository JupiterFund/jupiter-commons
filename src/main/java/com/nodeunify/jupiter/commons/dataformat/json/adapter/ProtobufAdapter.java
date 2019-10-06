package com.nodeunify.jupiter.commons.dataformat.json.adapter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtobufAdapter extends TypeAdapter<GeneratedMessageV3> {

    private Class<? extends GeneratedMessageV3> clazz;

    public ProtobufAdapter(Class<? extends GeneratedMessageV3> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void write(JsonWriter out, GeneratedMessageV3 value) throws IOException {
        out.jsonValue(JsonFormat.printer().omittingInsignificantWhitespace().print(value));
    }

    @Override
    public GeneratedMessageV3 read(JsonReader in) throws IOException {
        try {
            Method method = clazz.getMethod("newBuilder");
            Message.Builder builder = (Message.Builder) method.invoke(null);
            JsonParser jsonParser = new JsonParser();
            JsonFormat.parser().merge(jsonParser.parse(in).toString(), builder);
            return (GeneratedMessageV3) builder.build();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
