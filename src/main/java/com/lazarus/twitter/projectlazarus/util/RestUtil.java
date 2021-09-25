package com.lazarus.twitter.projectlazarus.util;


import com.lazarus.twitter.projectlazarus.exception.TwitterException;
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

    public Tweet getWithoutParameter(String url) throws TwitterException {
        MultiValueMap<java.lang.String, java.lang.String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION_HEADER,
                "Bearer b");
        try {
            var responseEntity = restTemplate.
                    exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
            return new MapperUtils().convertToObject(responseEntity.getBody(), "/data", Tweet.class);

        } catch (Exception e) {
            throw new TwitterException(e.getMessage(), e);
        }

    }

    public String postStatus(String headers, Signature signature) throws TwitterException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            var request = java.net.http.HttpRequest.newBuilder()
                    .uri(new
                            URI(STATUS_UPDATE + "?status="
                            + percentEncode(signature.getAuthorization().getStatus()) + "&" + "include_entities=true"))

                    .header(AUTHORIZATION_HEADER, OAUTH_HEADER + headers)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            var send = client.send(request, HttpResponse.BodyHandlers.ofString());
            return send.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TwitterException(e.getMessage(), e);

        } catch (Exception e) {

            throw new TwitterException(e.getMessage(), e);
        }
    }

    public String destroyStatus(String header, Signature signature) throws TwitterException {

        try {
            HttpClient client = HttpClient.newHttpClient();
            var request = java.net.http.HttpRequest.newBuilder()
                    .uri(new
                            URI(DELETE_TWEET + signature.getAuthorization().getId() + ".json"))
                    .header(AUTHORIZATION_HEADER, OAUTH_HEADER + header)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            var send = client.send(request, HttpResponse.BodyHandlers.ofString());
            return send.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TwitterException(e.getMessage(), e);
        } catch (Exception e) {
            throw new TwitterException(e.getMessage(), e);
        }
    }

    public String retweet(String header, Signature signature, String body) {
        MultiValueMap<java.lang.String, java.lang.String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION_HEADER, OAUTH_HEADER + header);
        headers.add("Content-Type", APPLICATION_JSON);
        headers.add("Accept", APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(signature.getUrl(), HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
        return responseEntity.getBody();
    }

    public String hideReplies(Signature signature, String header, String body) {

        MultiValueMap<java.lang.String, java.lang.String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION_HEADER, OAUTH_HEADER + header);
        headers.add("Content-Type", APPLICATION_JSON);
        headers.add("Accept", APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(signature.getUrl(), HttpMethod.PUT, new HttpEntity<>(body, headers), String.class);
        return responseEntity.getBody();
    }
}
