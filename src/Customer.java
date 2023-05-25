import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Product> inCart = new ArrayList<Product>();

    public Customer (String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    public void addToCart (Product product) {
        if (inCart.contains(product)) {
            System.out.println(product.getName() + " already in cart");
            return;
        }

        if(product.getStock() == 0) {
            System.out.println(product.getName() + " is out of stock");
            return;
        }
        inCart.add(product);
    }

    public void removeFromCart(Product product){
        if (inCart.contains(product)) {
            inCart.remove(product);
        }
    }

    public void viewCart() {
        double totalPrice = 0;
        System.out.println("Your cart contains:");

        for (Product product : inCart) {
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getCartQuantity());
            System.out.println();

            totalPrice += product.getPrice() * product.getCartQuantity();
        }
        System.out.println("Total price: " + totalPrice);
    }

    @Override
    public void handleActions(){
        
    }
}
