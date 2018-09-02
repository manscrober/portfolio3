import java.util.ArrayList;

public class ShoppingBasket {
    private Catalog catalog;
    private ArrayList<String> basket;
    public ShoppingBasket(Catalog catalog){
        this.catalog=catalog;
    }

    public void addItem(String name){
        if(catalog.getProductPrice(name)!=0) {
            basket.add(name);
        }else{
            System.out.println(name + " does not exist");
        }
    }
    public void deleteItem(String name){

    }
    public void delteItems(){

    }
    public void print(){
        int lengthOfLongestProductName = basket.stream().reduce((s1,s2)->s1.length()>s2.length()?s1:s2).get().length();
        String dashBarProduct=repeatSymbolNTimes('-',lengthOfLongestProductName);
        int lengthOfLongestPrice
                =Double.toString(basket.stream().map(s->catalog.getProductPrice(s))
                                                .reduce((d1,d2)->d1>d2?d1:d2)
                                                .get()).length();
        //one extra dash for the euro sign, one for the ',' symbol
        String dashBarPrice=repeatSymbolNTimes('-',lengthOfLongestPrice+2);
        System.out.println("+----"+dashBarProduct+"+"+dashBarPrice + "+");
        //7 is the length of "Produkt", 2 extra spaces for euro and comma. 5 is the length of "Preis".
        System.out.println("|Pos|Produkt"
                            + repeatSymbolNTimes(' ',lengthOfLongestProductName-7)
                            + "|Preis"+repeatSymbolNTimes(' ', lengthOfLongestPrice+2-5) + "|");
        for(int i=1;i<=basket.size();i++){
            //still needs to be made more compact. variables for name and price strings.
            //still not finished
            System.out.println("|"+repeatSymbolNTimes(' ',3-numberOfDigits(i))+"i"+"|"+basket.get(i)
                                + repeatSymbolNTimes(' ', lengthOfLongestProductName-basket.get(i).length())
                                + "|"
                                +repeatSymbolNTimes(' ',lengthOfLongestPrice
                                                                -numberOfDigits(catalog.getProductPrice(basket.get(i))))
                                +catalog.getProductPrice(basket.get(i)));
        }
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

    }
    public void printPackList(){

    }

}
