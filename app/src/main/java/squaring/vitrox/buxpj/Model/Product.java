package squaring.vitrox.buxpj.Model;

import java.io.Serializable;

/**
 * Created by miguelgomez on 10/17/16.
 */

public class Product {

    private String symbol;
    private String securityId;
    private String displayName;

    private Price currentPrice;

    private Price closingPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Price getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Price closingPrice) {
        this.closingPrice = closingPrice;
    }
}
