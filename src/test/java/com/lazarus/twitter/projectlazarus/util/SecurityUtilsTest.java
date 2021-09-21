package com.lazarus.twitter.projectlazarus.util;

import com.google.api.client.auth.oauth.OAuthHmacSha256Signer;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.lazarus.twitter.projectlazarus.security.Authorization;
import com.lazarus.twitter.projectlazarus.security.Signature;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    private SecurityUtils securityUtils;


    @Test
    void hmacCAlculationTest() {
        assertEquals("hCtSmYh+iHYCEqBWrE7C7hYmtUk=",
                SecurityUtils.
                        calculateHMAC("POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521", "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"));
    }


    @Test
    void urlEncoderTest() {
        assertEquals("this%20was%20LAZARUS", SecurityUtils.percentEncode("this was LAZARUS"));
    }

    @Test
    void urlEncoderTest2() {
        assertEquals("Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21",
                SecurityUtils.percentEncode("Hello Ladies + Gentlemen, a signed OAuth request!"));
    }

    @Test
    void securityTest() {
        Authorization authorization = new Authorization("xvz1evFS4wEEPTGEFPHBog",
                "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw",
                "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb",
                "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE", "Hello Ladies + Gentlemen, a signed OAuth request!");
        Signature signature = new Signature(authorization);
        authorization.setOauthNonce("kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg");
        authorization.setOauthTimeStamp("1318622958");
        assertEquals("hCtSmYh+iHYCEqBWrE7C7hYmtUk=" ,signature.generateSignature());
    }



}