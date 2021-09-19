package com.lazarus.twitter.projectlazarus.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * util classes to handle mapper
 */
public class MapperUtils {

    private  ObjectMapper objectMapper;

    public  <T> T convertToObject(String response, String path, Class<T> type) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response).at(path);
            return  objectMapper.treeToValue(jsonNode, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MapperUtils() {
        this.objectMapper = new ObjectMapper();
    }


}
