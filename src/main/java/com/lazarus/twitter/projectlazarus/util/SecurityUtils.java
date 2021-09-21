package com.lazarus.twitter.projectlazarus.util;

import com.lazarus.twitter.projectlazarus.security.Authorization;
import com.lazarus.twitter.projectlazarus.security.Signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.lazarus.twitter.projectlazarus.constant.SecurityConstants.HMAC_SHA1_ALGORITHM;

public class SecurityUtils {


    public static String generateTimeStamp() {

        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    public static String generateNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String percentEncode(String string) {

        try {
            return URLEncoder.encode(string, "UTF-8")
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String prepareParameterString(Authorization authorization) {
        StringBuilder builder = new StringBuilder();
        builder.append("include_entities=true").
                append("&").append("oauth_consumer_key=").append(percentEncode(authorization.getConsumerKey())).
                append("&").append("oauth_nonce=").append(percentEncode(authorization.getOauthNonce()))
                .append("&").append("oauth_signature_method=").append(percentEncode(authorization.getOauthSignatureMethod()))
                .append("&").append("oauth_timestamp=").append(percentEncode(authorization.getOauthTimeStamp()))
                .append("&").append("oauth_token=").append(percentEncode(authorization.getTokenKey()))
                .append("&").append("oauth_version=").append(percentEncode(authorization.getOauthVersion()))
                .append("&").append("status=").append(percentEncode(authorization.getStatus()));
        String[] input = builder.toString().split("&");
        Arrays.sort(input);
        return Arrays.stream(input).collect(Collectors.joining("&"));
    }

    public static String prepareSignatureBaseString(String paramaterString, String httpMethod, String url) {
        return httpMethod + "&" + percentEncode(url) + "&" + percentEncode(paramaterString);
    }

    public static String calculateSigningKey(Authorization authorization) {
        return
                authorization.getOauthConsumerSecret() + "&" + authorization.getTokenSecret();
    }


    public static String calculateHMAC(String input, String signingKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signingKey.getBytes(),
                    HMAC_SHA1_ALGORITHM);

            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(secretKeySpec);
            return Base64.getEncoder().encodeToString(mac.doFinal(input.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public static String getAuthorizationHeader(Signature status) {
        StringBuilder builder = new StringBuilder();
        appendParameter(builder, "oauth_consumer_key", status.getAuthorization().getConsumerKey());
        appendParameter(builder, "oauth_nonce", status.getAuthorization().getOauthNonce());
        appendParameter(builder, "oauth_signature", status.getSignatureString());
        appendParameter(builder, "oauth_signature_method", status.getAuthorization().getOauthSignatureMethod());
        appendParameter(builder, "oauth_timestamp", status.getAuthorization().getOauthTimeStamp());
        appendParameter(builder, "oauth_token", status.getAuthorization().getTokenKey());
        appendParameter(builder, "oauth_version", status.getAuthorization().getOauthVersion());


        return builder.substring(0, builder.length() - 1);

    }

    private static void appendParameter(StringBuilder buf, String name, String value) {
        if (value != null) {
            buf.append(" ").append(percentEncode(name)).append("=").append('"').append(percentEncode(value)).append("\",");
        }

    }
}
