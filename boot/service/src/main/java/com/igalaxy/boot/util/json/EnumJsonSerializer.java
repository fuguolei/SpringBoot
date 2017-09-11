package com.igalaxy.boot.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.igalaxy.boot.enums.base.ValueEnum;

import java.lang.reflect.Type;

/**
 * Created by jinlong on 16/8/4.
 */
public class EnumJsonSerializer implements JsonSerializer<ValueEnum> {
    @Override
    public JsonElement serialize(ValueEnum src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
            JsonObject object = new JsonObject();
            object.addProperty("name", src.getName().toString());
            object.addProperty("desc", src.getDesc());
            return object;
            //  return new JsonPrimitive(src.getDesc());

        }

        return null;
    }
}
