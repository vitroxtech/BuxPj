package squaring.vitrox.buxpj.Model;

/**
 * Created by miguelgomez on 10/17/16.
 */

public class Price {

    private String currency;
    private String decimals;
    private String amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDecimals() {
        return decimals;
    }

    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
