import java.util.ArrayList;

public class Store {
    //Session will write to file, and this will read from file
    //Every other class will deal with this, and at the end of the program, this will write to file

    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    
    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }
}
