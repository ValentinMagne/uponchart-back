package com.costardstudio.uponchart.models.ebay;

public class SellerFundsSummary {
    private Fund totalFunds;
    private Fund processingFunds;
    private Fund availableFunds;
    private Fund fundsOnHold;

    public Fund getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(Fund totalFunds) {
        this.totalFunds = totalFunds;
    }

    public Fund getProcessingFunds() {
        return processingFunds;
    }

    public void setProcessingFunds(Fund processingFunds) {
        this.processingFunds = processingFunds;
    }

    public Fund getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(Fund availableFunds) {
        this.availableFunds = availableFunds;
    }

    public Fund getFundsOnHold() {
        return fundsOnHold;
    }

    public void setFundsOnHold(Fund fundsOnHold) {
        this.fundsOnHold = fundsOnHold;
    }
}
