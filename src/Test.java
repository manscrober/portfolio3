public class Test {
    public static void main(String[] args) {
        Catalog catalog=new Catalog();
        catalog.addProduct("Akkuschrauber mit integrierter Armleuchte", 20000);
        catalog.addProduct("Zollstock Elfmeter", 990);
        catalog.addProduct("teuer",99999);
        ShoppingBasket sb = new ShoppingBasket(catalog);
        sb.addItem("Akkuschrauber mit integrierter Armleuchte");
        sb.addItem("Zollstock Elfmeter");
        sb.addItem("Akkuschrauber mit integrierter Armleuchte");
        for(int i=0;i<100;i++) {
            sb.addItem("teuer");
        }
        sb.print();
    }
}
