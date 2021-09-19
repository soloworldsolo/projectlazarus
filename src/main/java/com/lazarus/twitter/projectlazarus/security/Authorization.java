package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.constant.SecurityConstants;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;

import java.util.Objects;

public class Authorization {
    private String oauthCustomerKey;
    private String oauthNonce;
    private String oauthSignatureMethod;
    private String oauthTimeStamp;
    private String oauthToken;
    private String oauthVersion;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOauthCustomerKey() {
        return oauthCustomerKey;
    }

    public void setOauthCustomerKey(String oauthCustomerKey) {
        this.oauthCustomerKey = oauthCustomerKey;
    }

    public String getOauthNonce() {
        return oauthNonce;
    }

    public void setOauthNonce(String oauthNonce) {
        this.oauthNonce = oauthNonce;
    }

    public String getOauthSignatureMethod() {
        return oauthSignatureMethod;
    }

    public void setOauthSignatureMethod(String oauthSignatureMethod) {
        this.oauthSignatureMethod = oauthSignatureMethod;
    }

    public String getOauthTimeStamp() {
        return oauthTimeStamp;
    }

    public void setOauthTimeStamp(String oauthTimeStamp) {
        this.oauthTimeStamp = oauthTimeStamp;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public Authorization(String oauthCustomerKey, String oauthToken, String status) {
        this.oauthCustomerKey = oauthCustomerKey;
        this.oauthToken = oauthToken;
        this.status = Objects.requireNonNull(status);
        this.oauthTimeStamp = SecurityUtils.generateTimeStamp();
        this.oauthNonce = SecurityUtils.generateNonce();
        this.oauthVersion = SecurityConstants.OAUTH_VERSION_ONE;
        this.oauthSignatureMethod = SecurityConstants.SIGNATURE_METHOD;
    }
}
