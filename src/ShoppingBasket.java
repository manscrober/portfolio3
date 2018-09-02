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
        String dashBarPrice=repeatSymbolNTimes('-',lengthOfLongestPrice+1);//one extra dash for the euro sign
        System.out.println("+----"+dashBarProduct+"+"+dashBarPrice + "+");
    }
    private int numberOfDigits(double d){
        return Double.toString(d).length();
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
