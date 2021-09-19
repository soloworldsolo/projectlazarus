package com.lazarus.twitter.projectlazarus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazarus.twitter.projectlazarus.util.RestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * configuration class for spring boot to register spring beans
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestUtil getRestUtil() {
        return new RestUtil();
    }


}
