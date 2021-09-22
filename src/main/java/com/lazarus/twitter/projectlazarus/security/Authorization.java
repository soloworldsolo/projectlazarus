package com.lazarus.twitter.projectlazarus.security;

import com.lazarus.twitter.projectlazarus.constant.SecurityConstants;
import com.lazarus.twitter.projectlazarus.util.SecurityUtils;

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
    private String id;
    private String trimUser;
    private String includeEntities;

    private Authorization(AuthorizationBuilder authorization) {
        this.consumerKey = authorization.consumerKey;
        this.oauthConsumerSecret = authorization.oauthConsumerSecret;
        this.tokenKey = authorization.tokenKey;
        this.tokenSecret = authorization.tokenSecret;
        this.status = authorization.status;
        this.oauthTimeStamp = SecurityUtils.generateTimeStamp();
        this.oauthNonce = SecurityUtils.generateNonce();
        this.oauthVersion = SecurityConstants.OAUTH_VERSION_ONE;
        this.oauthSignatureMethod = SecurityConstants.SIGNATURE_METHOD;
        this.id = authorization.id;
        this.includeEntities = authorization.includeEntities;
    }

    public String getIncludeEntities() {
        return includeEntities;
    }

    public void setIncludeEntities(String includeEntities) {
        this.includeEntities = includeEntities;
    }

    public String getTrimUser() {
        return trimUser;
    }

    public void setTrimUser(String trimUser) {
        this.trimUser = trimUser;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class AuthorizationBuilder {

        private final String consumerKey;
        private final String oauthConsumerSecret;
        private final String tokenKey;
        private final String tokenSecret;
        private String status;
        private String id;
        private String trimUser;
        private String includeEntities;

        public AuthorizationBuilder(String consumerKey, String oauthConsumerSecret, String tokenKey, String tokenSecret) {
            this.consumerKey = consumerKey;
            this.oauthConsumerSecret = oauthConsumerSecret;
            this.tokenKey = tokenKey;
            this.tokenSecret = tokenSecret;
        }

        public AuthorizationBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public AuthorizationBuilder ofId(String id) {
            this.id = id;
            return this;
        }

        public AuthorizationBuilder trimUser(String trimUser) {
            this.trimUser = trimUser;
            return this;
        }

        public AuthorizationBuilder withEntities(String includeEntities) {
            this.includeEntities = includeEntities;
            return this;
        }

        public Authorization Build() {
            return new Authorization(this);
        }
    }


}
