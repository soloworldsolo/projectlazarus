package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.constant.URLConstants;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;
import org.springframework.http.HttpMethod;

/**
 * class to generate Oauth signature
 */
public class Signature {

    private Authorization authorization;
    private String signatureString;

    public Signature(Authorization authorization) {
        this.authorization = authorization;
        this.signatureString = generateSignature();

    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public String getSignatureString() {
        return signatureString;
    }

    public String generateSignature() {
        String preparedString = SecurityUtils.prepareParameterString(authorization);
        String signatureBaseString = SecurityUtils.prepareSignatureBaseString(preparedString, HttpMethod.POST.toString(),
                URLConstants.STATUS_UPDATE);
        String calculateSigningKey = SecurityUtils.calculateSigningKey(authorization);
        return SecurityUtils.calculateHMAC(signatureBaseString, calculateSigningKey);
    }
}
