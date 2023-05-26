import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Product> inCart = new ArrayList<Product>();
    ArrayList<Product> allProducts = Store.getAllProducts();

    public Customer(String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    // todo sort galaxy and sort planet in a list

    public void addToCart(Product product, int amount) {
        Session session = Session.getSession();
        if (inCart.contains(product)) {
            System.out.println(product.getName() + " already in cart");
            return;
        }

        if (product.getStock() == 0) {
            System.out.println(product.getName() + " is out of stock");
            return;
        }

        while(amount >= product.getStock()){
            System.out.println("Only " + product.getStock() + "of " + product.getName() + " available.");
            System.out.println("Please enter amount within this range: ");
            amount = session.readInt();
        }
        //change product incart quantity
        product.setCartQuantity(amount);
        inCart.add(product);
        
    }

    public void removeFromCart(Product product) {
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

    public Product searchItem(String name) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    @Override
    public void handleActions() {
        Session session = Session.getSession();
        do {
            System.out.println("1. View all items    2. View items in cart    3. Log out");
            int choice = session.readInt();
            switch (choice) {
                case 1:
                    Store.viewAllProducts();
                    boolean back = false;
                    do {
                        System.out.println("1. Add item to cart    2. Back");
                        int choice2 = session.readInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("Enter name of item to add to cart: ");
                                String name = session.scanString.nextLine();

                                Product product = searchItem(name);

                                if (product == null) {
                                    System.out.println("Item does not exist");
                                } else {

                                    System.out.println("Enter amount of " + name + " to add: ");
                                    int amount = session.readInt();
                                    addToCart(product, amount);
                                }
                                break;
                            case 2:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                        break;
                    } while (!back);
                case 2:
                    viewCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        } while (true);
    }
}
