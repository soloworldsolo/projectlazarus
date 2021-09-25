package com.lazarus.twitter.projectlazarus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

/**
 * util classes to handle mapper
 */
public class MapperUtils {

    private final ObjectMapper objectMapper;

    public MapperUtils() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T convertToObject(String response, String path, Class<T> type) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response).at(path);
            return objectMapper.treeToValue(jsonNode, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String convertObjectToString(T input) {
        try {
            return objectMapper.writeValueAsString(Objects.requireNonNull(input));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <K,V>String convertMapToString(Map<K, V> map) {
        try {
            return objectMapper.writeValueAsString(Objects.requireNonNull(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
