package com.lin.missyou.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.missyou.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericAndJson {

    private static ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper){
        GenericAndJson.mapper = mapper;
    }

    public static <T> String objectToJson(T t){
        try {
            return GenericAndJson.mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    public static <T> T jsonToObject(String s, Class<T> tClass){
        try {
            if(s == null){
                return null;
            }
            return GenericAndJson.mapper.readValue(s, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    public static <T> T jsonToList(String s, TypeReference<T> tr){
        try {
            if(s == null){
                return null;
            }
            T list = GenericAndJson.mapper.readValue(s, tr);
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }
}
