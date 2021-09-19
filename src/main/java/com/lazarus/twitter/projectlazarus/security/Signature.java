package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.util.SecurityUtils;

/**
 * class to generate Oauth signature
 */
public class Signature {

    private Authorization authorization;

    public Signature(Authorization authorization) {
        this.authorization = authorization;
    }

    public String generateSignature() {
        String preparedString = SecurityUtils.preparedString(authorization);
        return null;
    }
}
