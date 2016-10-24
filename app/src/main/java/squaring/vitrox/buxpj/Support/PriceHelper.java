package squaring.vitrox.buxpj.Support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class PriceHelper {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public String getshowablePrice(String value, String currency, String decimal) {
        if (!value.isEmpty() && !currency.isEmpty() && !decimal.isEmpty()) {
            StringBuilder Sb = new StringBuilder();
            Currency mycurrency = Currency.getInstance(currency);
            BigDecimal decimal1 = new BigDecimal(value);
            decimal1 = decimal1.setScale(Integer.parseInt(decimal), RoundingMode.DOWN);
            Sb.append(mycurrency);
            Sb.append(" ");
            Sb.append(decimal1.toString());
            return Sb.toString();
        }
        return null;
    }

    public String getpercentDiff(String oldVal, String newVal, String decimal) {
        if (!oldVal.isEmpty() && !newVal.isEmpty()) {
            BigDecimal decA = new BigDecimal(oldVal);
            BigDecimal decB = new BigDecimal(newVal);
            int dec= Integer.parseInt(decimal);
            BigDecimal result;
            result = ONE_HUNDRED.multiply(BigDecimal.ONE.subtract((decA.divide(decB,dec+3, BigDecimal.ROUND_DOWN))));
            String resToshow = result.toPlainString() + " %";
            return resToshow;
        }

        return null;
    }
}
