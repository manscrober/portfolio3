import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShoppingBasket {

    public static void main(String[] args) {

    }


    private final String TABLE_LINE_SEPERATOR = "+-----------------------------------+-------+";
    private final String TABLE_HEADER = TABLE_LINE_SEPERATOR
                                        + "\n|Pos|Produkt                        |Preis  |\n" +
                                        TABLE_LINE_SEPERATOR;
    private final int COLUMN_POSITION_LENGTH = 3;
    private final int COLUMN_NAME_LENGTH = 31;
    private final int COLUMN_PRICE_LENGTH = 7;
    private final int LINE_SUM_LENGTH = 43;
    private final int SUM_NAME_LENGTH = 7;
    private final int CENT_DECIMAL_LENGTH=2;
    private final String SUM_NAME = "Summe: ";

    private Catalog catalog;
    private ArrayList<String> basket;

    public ShoppingBasket(Catalog catalog) {
        this.catalog = catalog;
        basket = new ArrayList<>();
    }

    public void addItem(String name) {
        if (catalog.hasProduct(name)) {
            basket.add(name);
        } else {
            System.out.println(name + " does not exist");
        }
    }

    public void deleteItem(int position) {
        int indexInArrayList = position - 1;
        if (basket.get(indexInArrayList) != null) {
            basket.remove(indexInArrayList);
        } else {
            System.out.println("product not found");
        }
    }

    public void deleteItems(String name) {
        if (basket.contains(name)) {
            basket.removeIf(item -> item.equals(name));
        } else {
            System.out.println(name + " is not in the shopping basket");
        }
    }

    public void print() {

        System.out.println(TABLE_HEADER);
        for (int i = 0; i < basket.size(); i++) {
            int positionNumber = i + 1;
            String positionSpaces = repeatSymbolNTimes(' ', COLUMN_POSITION_LENGTH - numberOfDigits(positionNumber));
            String productName = basket.get(i);
            String nameSpaces = repeatSymbolNTimes(' ', COLUMN_NAME_LENGTH - productName.length());
            String priceString = convertCentIntToEuroString(catalog.getProductPrice(productName));
            String priceSpaces = repeatSymbolNTimes(' ', COLUMN_PRICE_LENGTH - priceString.length());
            String nameString;
            if (productName.length() > COLUMN_NAME_LENGTH) {
                nameString = productName.substring(0, COLUMN_NAME_LENGTH);
            } else {
                nameString = productName + nameSpaces;
            }
            System.out.println("|" + positionSpaces + (positionNumber) + "|" + nameString + "|" + priceSpaces + priceString + "|");

        }
        System.out.println(TABLE_LINE_SEPERATOR);
        String sumString = convertCentIntToEuroString(sumProducts());
        String sumStringSpaces = repeatSymbolNTimes(' ', LINE_SUM_LENGTH - sumString.length() - SUM_NAME.length());
        System.out.println("|" + sumStringSpaces + SUM_NAME + sumString + "|");
        System.out.println(TABLE_LINE_SEPERATOR);
    }

    private String convertCentIntToEuroString(int cents) {

        String priceStringCents = ""+cents;
        while(priceStringCents.length()<=CENT_DECIMAL_LENGTH){
            priceStringCents = "0" + priceStringCents;
        }
        String priceString = priceStringCents.substring(0, priceStringCents.length() - CENT_DECIMAL_LENGTH)
                + "," + priceStringCents.substring(priceStringCents.length() - CENT_DECIMAL_LENGTH) + "€";
        return priceString;
    }

    private int sumProducts() {
        return basket.stream().map(s -> catalog.getProductPrice(s))
                     .reduce((price, currentPrice) -> price + currentPrice).orElse(0);
    }

    private int numberOfDigits(int i) {
        return Integer.toString(i).length();
    }

    private String repeatSymbolNTimes(Character symbol, int n) {
        String string = "";
        for (int i = 0; i < n; i++) {
            string += symbol;
        }
        return string;
    }

    public void sortByName() {
        Collections.sort(basket);
    }

    public void printPackList() {
        HashMap<String, Integer> itemCounts = new HashMap<>();
        basket.stream().forEach(s -> itemCounts.put(s, itemCounts.get(s) == null ? 1 : itemCounts.get(s) + 1));
        itemCounts.forEach((name, count) -> System.out.println(name + ": " + count));
    }

}
