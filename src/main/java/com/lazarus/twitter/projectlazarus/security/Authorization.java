package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.constant.SecurityConstants;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;

import java.util.Objects;

public class Authorization {
    private String consumerKey;
    private String oauthConsumerSecret;
    private String oauthNonce;
    private String oauthSignatureMethod;
    private String oauthTimeStamp;
    private String tokenKey;
    private String tokenSecret;
    private String oauthVersion;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
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

    public String getOauthConsumerSecret() {
        return oauthConsumerSecret;
    }

    public void setOauthConsumerSecret(String oauthConsumerSecret) {
        this.oauthConsumerSecret = oauthConsumerSecret;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public Authorization(String oauthCustomerKey, String oauthConsumerSecret, String oauthTokenKey,
                         String oauthTokenSecret, String status) {
        this.consumerKey = oauthCustomerKey;
        this.oauthConsumerSecret = oauthConsumerSecret;
        this.tokenKey = oauthTokenKey;
        this.tokenSecret = oauthTokenSecret;
        this.status = Objects.requireNonNull(status);
        this.oauthTimeStamp = SecurityUtils.generateTimeStamp();
        this.oauthNonce = SecurityUtils.generateNonce();
        this.oauthVersion = SecurityConstants.OAUTH_VERSION_ONE;
        this.oauthSignatureMethod = SecurityConstants.SIGNATURE_METHOD;
    }
}
