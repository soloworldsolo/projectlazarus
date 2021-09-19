package com.lazarus.twitter.projectlazarus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Util class for String
 */
@Component
public class RestUtil {
    @Autowired
    RestTemplate restTemplate;

    public Tweet getWithoutParameter(String url) {
        MultiValueMap<java.lang.String, java.lang.String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAOD%2F9QAAAAAAHhnSg78qKRtP%2FFW2yBl1I7o7Oz0%3DqnsS3z9ANbkpBGIz5mfFk8OiUJesDjFY7UD2f8mkTc5j1wbPIH");
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(url,HttpMethod.GET,new HttpEntity<>(headers), String.class);
        String res = responseEntity.getBody();

        return  new MapperUtils().convertToObject(res,"/data",Tweet.class);
    }
}
