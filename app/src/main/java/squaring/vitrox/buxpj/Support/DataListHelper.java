package squaring.vitrox.buxpj.Support;

import java.util.ArrayList;

public class DataListHelper {

    private ArrayList<String> productNameList;
    private ArrayList<String> productCodeList;

    public DataListHelper() {
        productNameList = new ArrayList<>();
        productCodeList = new ArrayList<>();
        productNameList.add("Germany30");
        productCodeList.add("sb26493");
        productNameList.add("US500");
        productCodeList.add("sb26496");
        productNameList.add("EUR/USD");
        productCodeList.add("sb26502");
        productNameList.add("Gold");
        productCodeList.add("sb26500");
        productNameList.add("Apple");
        productCodeList.add("sb26513");
        productNameList.add("Deutsche Bank");
        productCodeList.add("sb28248");
    }

    public ArrayList<String> getNameList() {
        return productNameList;
    }

    public ArrayList<String> getProductCodeList() {
        return productCodeList;
    }

    public String getIdIndexOf(int i) {
        return productCodeList.get(i);
    }


}
