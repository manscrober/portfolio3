import java.util.HashMap;

public class Catalog {
    private final int CENT_DECIMAL_LENGTH=2;
    private HashMap<String, Integer> products;
    public Catalog(){
        products=new HashMap<>();
    }

    public void addProduct(String name, int price){
        if(products.containsKey(name)) {
            System.out.println(name + " already exists");
        }else{
            products.put(name, price);
        }
    }
    public void updateProduct(String name, int price){
        if(products.containsKey(name)){
            products.put(name, price);
        }else{
            System.out.println(name+" does not exist");
        }
    }
    public void deleteProduct(String name){
        if(products.containsKey(name)) {
            products.remove(name);
        }else{
            System.out.println(name + " does not exist");
        }
    }

    public void showProduct(String name){
        if(products.containsKey(name)){
            System.out.println(name+ ", Preis:" + convertCentIntToEuroString(products.get(name)));
        }else{
            System.out.println(name + " does not exist");
        }
    }


    private String convertCentIntToEuroString(int cents) {

        String priceStringCents = Integer.toString(cents);
        while(priceStringCents.length()<=2){
            priceStringCents = "0" + priceStringCents;
        }
        String priceString = priceStringCents.substring(0, priceStringCents.length() - CENT_DECIMAL_LENGTH)
                + "," + priceStringCents.substring(priceStringCents.length() - CENT_DECIMAL_LENGTH) + "€";
        return priceString;
    }


    public int getProductPrice(String name){

        if(products.containsKey(name)){
            return products.get(name);
        }else{
            return 0;//assuming there aren't any free products
        }
    }
    public boolean hasProduct(String name){
        return products.containsKey(name);
    }
}
