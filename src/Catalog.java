import java.util.HashMap;

public class Catalog {
    private HashMap<String, Integer> products;

    public Catalog(){

    }

    public void addProduct(String name, int price){
        if(products.get(name)==null) {
            products.put(name, price);
        }else{
            System.out.println(name + " already exists");
        }
    }
    public void deleteProduct(String name){
        if(products.get(name)!=null) {
            products.remove(name);
        }else{
            System.out.println(name + " does not exist");
        }
    }
    public void updateProduct(String name, int price){
        if(products.get(name)!=null){
            products.put(name,price);
        }else{
            System.out.println(name+" does not exist");
        }
    }
    public void showProduct(String name){
        if(products.get(name)!= null){
            System.out.println(name+ ", Preis:" + products.get(name));
        }else{
            System.out.println(name + " does not exist");
        }
    }
    public int getProductPrice(String name){

        if(products.get(name)!=null){
            return products.get(name);
        }else{
            return 0;//unter der annahme, dass es keine kostenlosen produkte gibt.
        }
    }
    public void hasProduct(){

    }
}
