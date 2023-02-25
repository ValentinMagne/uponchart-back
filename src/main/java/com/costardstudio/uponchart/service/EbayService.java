package com.costardstudio.uponchart.service;

import com.costardstudio.uponchart.configuration.PropertiesConfiguration;
import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.models.UserAccessToken;
import com.costardstudio.uponchart.models.ebay.SellerFundsSummary;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Base64;

@Service
public class EbayService {

    private final UserService userService;
    private final RestTemplateBuilder restTemplateBuilder;
    private final PropertiesConfiguration configuration;

    public EbayService(UserService userService, RestTemplateBuilder restTemplateBuilder, PropertiesConfiguration configuration) {
        this.userService = userService;
        this.restTemplateBuilder = restTemplateBuilder;
        this.configuration = configuration;
    }

    public SellerFundsSummary getSellerFundsSummary() throws UserPrincipalNotFoundException {
        String url = this.configuration.getEbayEndpointsUrl() + "/sell/finances/v1/seller_funds_summary";
        UserEntity userEntity = this.userService.getCurrentUser();
        String accessToken = userEntity.getAccessToken();
        RestTemplate restTemplate = restTemplateBuilder.additionalInterceptors((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, body);
        }).build();
        ResponseEntity<SellerFundsSummary> response = restTemplate.getForEntity(url, SellerFundsSummary.class);
        return response.getBody();
    }

    public UserAccessToken refreshAccessToken(String refreshToken) {
        return this.postToken(refreshToken, true);
    }

    public UserAccessToken exchangeConsentToken(String consentToken) {
        return this.postToken(consentToken, false);
    }

    private UserAccessToken postToken(String token, Boolean refresh) {
        String url = this.configuration.getEbayAuthUrl();
        String appID = this.configuration.getEbayAppId();
        String certID = this.configuration.getEbayCertId();
        String redirectURI = this.configuration.getEbayRedirectURI();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((appID + ":" + certID).getBytes()));
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        if (refresh) {
            body.add("grant_type", "refresh_token");
            body.add("refresh_token", token);
        } else {
            body.add("grant_type", "authorization_code");
            body.add("code", token);
            body.add("redirect_uri", redirectURI);
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserAccessToken> response = restTemplate.postForEntity(url, requestEntity, UserAccessToken.class);
        return response.getBody();
    }
}
