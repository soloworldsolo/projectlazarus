package com.lazarus.twitter.projectlazarus.util;

import com.lazarus.twitter.projectlazarus.security.Authorization;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class SecurityUtils {


    public static String generateTimeStamp() {
        return String.valueOf(Timestamp.from(Instant.now()).getTime());
    }

    public static String generateNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static  String percentEncode(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8);
    }

    public static String preparedString(Authorization authorization) {
        StringBuilder builder = new StringBuilder();
        builder.append("")
        return builder.toString()
    }
}
