import java.util.HashMap;

public class Catalog {
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
            products.put(name,price);
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
