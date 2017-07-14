package com.igalaxy.boot.util.json;

import com.google.gson.*;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.enums.base.ValueEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jinlong on 2017/2/6.
 */
public class AppJsonUtils {

    public static String toJsonOld(Object data) {
        String result = getDefaultGsonBuilder()
                .create().toJson(data);
        return result;
    }

    public static String toJson(BaseResult appResult, String[] fileds) {
        JsonObject jsonObject = gsonExpose.toJsonTree(appResult).getAsJsonObject();
        if (appResult.getData() == null) {
            return jsonObject.toString();
        }

        jsonObject.add("data", beanToJsonObject(appResult.getData(), fileds));

        return jsonObject.toString();
    }

    private static JsonElement beanToJsonObject(Object bean, String[] fields) {
        if (bean == null) {
            return null;
        }

        if (bean instanceof String) {
            return new JsonPrimitive(bean.toString());
        }

        //需要过滤掉的属性
        if (fields == null) {
            return gsonData.toJsonTree(bean);

        } else {
            Gson gson = getDefaultGsonBuilder()
                    .setExclusionStrategies(new FilterFiledExclusionStrategy(fields))
                    .create();
            return gson.toJsonTree(bean);
        }
    }


    static Gson gsonExpose = getDefaultGsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    static final String[] fields = {"createUser", "updateUser", "disabled"};
    static Gson gsonData = getDefaultGsonBuilder()
            .setExclusionStrategies(new FilterFiledExclusionStrategy(fields))
            .create();


    public static GsonBuilder getDefaultGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(ValueEnum.class, new EnumJsonSerializer())
                .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    static class FilterFiledExclusionStrategy implements ExclusionStrategy {
        private Set<String> filedSet = new HashSet<>();

        public FilterFiledExclusionStrategy(String[] fileds) {
            if (fileds != null) {
                for (String s : fileds) {
                    filedSet.add(s);
                }
            }
        }

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return filedSet.contains(fa.getName());
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

}
