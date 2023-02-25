package com.costardstudio.uponchart.controller;

import com.costardstudio.uponchart.entity.UserEntity;
import com.costardstudio.uponchart.models.ConsentToken;
import com.costardstudio.uponchart.models.UserAccessToken;
import com.costardstudio.uponchart.models.ebay.SellerFundsSummary;
import com.costardstudio.uponchart.security.AuthoritiesConstants;
import com.costardstudio.uponchart.service.EbayService;
import com.costardstudio.uponchart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbayController {

    private final UserService userService;
    private final EbayService ebayService;

    public EbayController(UserService userService, EbayService ebayService) {
        this.userService = userService;
        this.ebayService = ebayService;
    }

    @PostMapping("/ebay/token")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.USER + "')")
    public ResponseEntity<Void> exchangeConsentToken(@RequestBody ConsentToken consentToken) {
        try {
            UserAccessToken userAccessToken = this.ebayService.exchangeConsentToken(consentToken.token);
            this.userService.saveAccessToken(userAccessToken);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ebay/sell/finance/seller-funds-summary")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.USER + "')")
    public ResponseEntity<SellerFundsSummary> getSellerFundsSummary() {
        try {
            SellerFundsSummary sellerFundsSummary = this.ebayService.getSellerFundsSummary();
            return ResponseEntity.ok(sellerFundsSummary);
        } catch (Exception error) {
            try {
                UserEntity user = this.userService.getCurrentUser();
                UserAccessToken userAccessToken = this.ebayService.refreshAccessToken(user.getRefreshToken());
                this.userService.saveAccessToken(userAccessToken);
                SellerFundsSummary sellerFundsSummary = this.ebayService.getSellerFundsSummary();
                return ResponseEntity.ok(sellerFundsSummary);
            } catch (Exception retryError) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
