package com.lazarus.twitter.projectlazarus.util;


import com.google.api.client.auth.oauth.OAuthParameters;
import com.lazarus.twitter.projectlazarus.constant.URLConstants;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.security.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.lazarus.twitter.projectlazarus.constant.URLConstants.*;
import static com.lazarus.twitter.projectlazarus.util.SecurityUtils.percentEncode;

/**
 * Util class for String
 */
@Component
public class RestUtil {
    @Autowired
    RestTemplate restTemplate;

    public Tweet getWithoutParameter(String url) {
        MultiValueMap<java.lang.String, java.lang.String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization",
                "Bearer AAAAAAAAAAAAAAAAAAAAAOD%2F9QAAAAAAHhnSg78qKRtP%2FFW2yBl1I7o7Oz0%3DqnsS3z9ANbkpBGIz5mfFk8OiUJesDjFY7UD2f8mkTc5j1wbPIH");
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        String res = responseEntity.getBody();

        return new MapperUtils().convertToObject(res, "/data", Tweet.class);
    }

    public String postStatus(String headers, Signature signature) {
        String statusURL = STATUS_UPDATE + "?status=" + percentEncode(signature.
                getAuthorization().getStatus() );
        try {
            HttpClient client = HttpClient.newHttpClient();

           var request = java.net.http.HttpRequest.newBuilder()
                    .uri(new URI(STATUS_UPDATE+"?status="+percentEncode(signature.getAuthorization().getStatus())))
                    .header("Authorization" ,"OAuth"+headers)
                   .header("Content-Type","application/json")
                   .header("Accept","application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            var send = client.send(request, HttpResponse.BodyHandlers.ofString());
            return send.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}
