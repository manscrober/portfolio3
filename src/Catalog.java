import java.util.HashMap;

public class Catalog {
    private HashMap<String, Integer> products;
    private final int PRICE_LIMIT=10000;
    public Catalog(){
        products=new HashMap<>();
    }

    public void addProduct(String name, int price){
        if(products.containsKey(name)) {
            System.out.println(name + " already exists");
        }else if(price<PRICE_LIMIT){
            products.put(name, price);
        }else{
            System.out.println("price too high");
        }
    }
    public void updateProduct(String name, int price){
        if(products.containsKey(name)){
            if(price<PRICE_LIMIT) {
                products.put(name, price);
            }else{
                System.out.println("price is too high");
            }
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
            System.out.println(name+ ", Preis:" + products.get(name));
        }else{
            System.out.println(name + " does not exist");
        }
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
