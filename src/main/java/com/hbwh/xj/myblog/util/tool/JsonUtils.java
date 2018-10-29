package com.hbwh.xj.myblog.util.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbwh.xj.myblog.po.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static String stringify(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    public static Object parse(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, Object.class);
    }

    public static <T> T parseBean(String json, Class<T> cls) throws IOException {
        return (T) OBJECT_MAPPER.readValue(json, cls);
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> kCls, Class<V> vCls) throws IOException {
        JavaType jt = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class,
                kCls, vCls);
        return OBJECT_MAPPER.readValue(json, jt);
    }

    public static <T> List<T> parseList(String json, Class<T> cls) throws IOException {
        JavaType jt = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class,
                cls);
        return OBJECT_MAPPER.readValue(json, jt);
    }

    public static void main(String[] args) throws Throwable{
        /*User user = new User();
        System.out.println(stringify(user));

        Map<String, User> map = new HashMap<>();
        map.put("key", user);
        System.out.println(stringify(map));

        List<User> list = new ArrayList<>();
        list.add(user);
        System.out.println(stringify(list));*/

        String json = "{\"userid\":\"001\"}";
        System.out.println(parseBean(json, User.class));

//        String mapJson = "{\"key\":" + json + "}";
        String mapJson = "{\"key-我\": \"va-我lue\"}";
        System.out.println(parseMap(mapJson, String.class, String.class));
        System.out.println(parseBean(mapJson, Object.class));

        String listJson = "[\"001\"]";
        System.out.println(parseList(listJson, String.class));
        System.out.println(parseBean(listJson, Object.class));
    }
}
