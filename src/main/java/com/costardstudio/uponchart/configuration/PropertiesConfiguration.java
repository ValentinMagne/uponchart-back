package com.costardstudio.uponchart.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("uponchart.config")
public class PropertiesConfiguration {

    private String corsAllowedOrigins;
    private String ebayEndpointsUrl;
    private String ebayAuthUrl;
    private String ebayAppId;
    private String ebayCertId;
    private String ebayRedirectURI;

    public String getCorsAllowedOrigins() {
        return corsAllowedOrigins;
    }

    public void setCorsAllowedOrigins(String corsAllowedOrigins) {
        this.corsAllowedOrigins = corsAllowedOrigins;
    }

    public String getEbayEndpointsUrl() {
        return ebayEndpointsUrl;
    }

    public void setEbayEndpointsUrl(String ebayEndpointsUrl) {
        this.ebayEndpointsUrl = ebayEndpointsUrl;
    }

    public String getEbayAuthUrl() {
        return ebayAuthUrl;
    }

    public void setEbayAuthUrl(String ebayAuthUrl) {
        this.ebayAuthUrl = ebayAuthUrl;
    }

    public String getEbayAppId() {
        return ebayAppId;
    }

    public void setEbayAppId(String ebayAppId) {
        this.ebayAppId = ebayAppId;
    }

    public String getEbayCertId() {
        return ebayCertId;
    }

    public void setEbayCertId(String ebayCertId) {
        this.ebayCertId = ebayCertId;
    }

    public String getEbayRedirectURI() {
        return ebayRedirectURI;
    }

    public void setEbayRedirectURI(String ebayRedirectURI) {
        this.ebayRedirectURI = ebayRedirectURI;
    }
}

