package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.util.SecurityUtils;
import org.springframework.http.HttpMethod;

/**
 * class to generate Oauth signature
 */
public class Signature {

    private final Authorization authorization;
    private final String signatureString;
    private final String httpMethod;
    private final String url;

    public Signature(Authorization authorization, String httpMethod, String url) {
        this.authorization = authorization;
        this.httpMethod = httpMethod;
        this.url = url;
        this.signatureString = generateSignature();

    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public String getSignatureString() {
        return signatureString;
    }

    public String getUrl() {
        return url;
    }

    public String generateSignature() {
        String preparedString = SecurityUtils.prepareParameterString(authorization);
        String signatureBaseString = SecurityUtils.prepareSignatureBaseString(preparedString, this.httpMethod,
                url);
        String calculateSigningKey = SecurityUtils.calculateSigningKey(authorization);
        return SecurityUtils.calculateHMAC(signatureBaseString, calculateSigningKey);
    }
}
