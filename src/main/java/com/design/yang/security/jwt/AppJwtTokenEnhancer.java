package com.design.yang.security.jwt;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class AppJwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//        Map<String,String> token = new HashMap<>();
//        token.put("Editor","yang");
//        oAuth2AccessToken.getAdditionalInformation().putAll(token);
        return oAuth2AccessToken;
    }
}
