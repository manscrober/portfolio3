import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShoppingBasket {
    private final String TABLE_HEADER =         "+-----------------------------------+-------+\n" +
                                                "|Pos|Produkt                        |Preis  |\n" +
                                                "+-----------------------------------+-------+";
    private final String TABLE_LINE_SEPERATOR = "+-----------------------------------+-------+";
    private final int COLUMN_POSITION_LENGTH=3;
    private final int COLUMN_NAME_LENGTH=31;
    private final int COLUMN_PRICE_LENGTH=7;
    private final int LINE_SUM_LENGTH=43;
    private final int SUM_NAME_LENGTH=7;
    private final int BASKET_LIMIT = 1000;
    private final String SUM_NAME = "Summe: ";

    private Catalog catalog;
    private ArrayList<String> basket;
    public ShoppingBasket(Catalog catalog){
        this.catalog=catalog;
        basket=new ArrayList<>();
    }

    public void addItem(String name){
        if(catalog.hasProduct(name)) {
            if(basket.size()<BASKET_LIMIT) {
                basket.add(name);
            }else{
                System.out.println("basket is full");
            }
        }else{
            System.out.println(name + " does not exist");
        }
    }
    public void deleteItem(int position){
        int indexInArrayList=position-1;
        if(basket.get(indexInArrayList)!=null){
            basket.remove(indexInArrayList);
        }else{
            System.out.println("product not found");
        }
    }
    public void delteItems(String name){
        if(basket.contains(name)){
            ArrayList<String> toRemove=new ArrayList<>();
            toRemove.add(name);
            basket.removeAll(toRemove);
        }else{
            System.out.println(name + " is not in the shopping basket");
        }
    }
    public void print(){

        System.out.println(TABLE_HEADER);
        for(int i=0;i<basket.size();i++){
            int positionnumber=i+1;
            String positionSpaces=repeatSymbolNTimes(' ',COLUMN_POSITION_LENGTH-numberOfDigits(positionnumber));
            String nameSpaces=repeatSymbolNTimes(' ',COLUMN_NAME_LENGTH-basket.get(i).length());
;
            String priceString=convertCentIntToEuroString(catalog.getProductPrice(basket.get(i)));
            String priceSpaces=repeatSymbolNTimes(' ',COLUMN_PRICE_LENGTH-priceString.length());
            String nameString;
            if(basket.get(i).length()>COLUMN_NAME_LENGTH) {
                nameString= basket.get(i).substring(0, COLUMN_NAME_LENGTH);
            }else{
                nameString=basket.get(i)+nameSpaces;
            }
            System.out.println("|"+positionSpaces+(positionnumber)+"|"+nameString+"|"+priceSpaces+priceString+"|");

        }
        System.out.println(TABLE_LINE_SEPERATOR);
        String sumstring=convertCentIntToEuroString(sumProducts());
        String sumStringSpaces=repeatSymbolNTimes(' ',LINE_SUM_LENGTH-sumstring.length()-SUM_NAME_LENGTH);
        System.out.println("|"+sumStringSpaces+SUM_NAME+sumstring+"|");
        System.out.println(TABLE_LINE_SEPERATOR);
    }
    private String convertCentIntToEuroString(int cents){

        String priceStringCents=Integer.toString(cents);
        String priceString=priceStringCents.substring(0,priceStringCents.length()-2)
                +","+priceStringCents.substring(priceStringCents.length()-2) + "€";
        return priceString;
    }
    private int sumProducts(){
        return basket.stream().map(s->catalog.getProductPrice(s)).reduce((price,currentPrice)->price+currentPrice).get();
    }
    private int numberOfDigits(int i){
        return Integer.toString(i).length();
    }
    private String repeatSymbolNTimes(Character symbol, int n){
        String string = "";
        for(int i=0;i<n;i++){
            string+=symbol;
        }
        return string;
    }
    public void sortByName(){
        Collections.sort(basket);
    }
    public void printPackList(){
        HashMap<String,Integer> itemCounts=new HashMap<>();
        basket.stream().forEach(s->itemCounts.put(s,itemCounts.get(s)==null?1:itemCounts.get(s)+1));
        itemCounts.forEach((name,count)->System.out.println(name+": "+count));
    }

}
