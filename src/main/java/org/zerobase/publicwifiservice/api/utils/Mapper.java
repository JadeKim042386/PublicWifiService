package org.zerobase.publicwifiservice.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode stringToJsonNode(String content) {
        try {
            return MAPPER.readTree(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringToObject(String content, Class<T> clazz) {
        try {
            return MAPPER.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
