package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.constant.URLConstants;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;
import org.springframework.http.HttpMethod;

import java.util.Objects;

/**
 * class to generate Oauth signature
 */
public class Signature {

    private Authorization authorization;
    public String signature;

    public Signature(Authorization authorization) {
        this.authorization = authorization;
        this.signature = generateSignature();

    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public String getSignature() {
        return signature;
    }

    public String generateSignature() {
        String preparedString = SecurityUtils.prepareParameterString(authorization);
        String signatureBaseString = SecurityUtils.prepareSignatureBaseString(preparedString, HttpMethod.POST.toString(),
                URLConstants.STATUS_UPDATE);
        String calculateSigningKey = SecurityUtils.calculateSigningKey(authorization);
        return SecurityUtils.calculateHMAC(signatureBaseString,calculateSigningKey);
    }
}
