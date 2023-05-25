import java.util.ArrayList;

public class Store {
    //Session will write to file, and this will read from file
    //Every other class will deal with this, and at the end of the program, this will write to file

    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    
    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static void viewAllProducts(){
        System.out.println("All products:");
        for (Product product : allProducts) {
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("Planet: " + product.getPlanet());
            System.out.println("Galaxy: " + product.getGalaxy());
            System.out.println("Condition: " + product.getCondition());
            System.out.println();
        }
    }

    
}
