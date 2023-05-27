import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<cartProduct> inCart = new ArrayList<cartProduct>();
    ArrayList<Product> allProducts = Store.getAllProducts();

    public Customer(String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    public void addToCart(String name) {
        Session session = Session.getSession();
        Product product = searchItem(name);
        int amount = 0;
        if (product == null) {
            System.out.println("Item does not exist");
            return;
        } else {

            System.out.println("Enter amount of " + name + " to add: ");
            amount = session.readInt();
        }

        if (product.getStock() == 0) {
            System.out.println(product.getName() + " is out of stock");
            return;
        }

        while (amount > product.getStock()) {
            System.out.println("Only " + product.getStock() + " of " + product.getName() + " available.");
            System.out.println("Please enter amount within this range: ");
            amount = session.readInt();
        }

        for (cartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                System.out.println("Item already in cart. Do you want to update the item? (y/n)");
                String choice = session.scanString.nextLine();
                if (choice.toLowerCase().equals("y")) {
                    // cartProduct.setQuantity(cartProduct.getQuantity() + amount);
                    updateCartItem(name);
                    return;
                }
                return;
            }
        }

        // change product incart quantity
        product.setStock(product.getStock() - amount);
        inCart.add(new cartProduct(product, amount));

    }

    public void removeFromCart(String name) {
        Product product = searchItem(name);
        if (product == null) {
            System.out.println("Item does not exist");
            return;
        }
        for (cartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                inCart.remove(cartProduct);
                return;
            }
        }
        System.out.println("Item not in cart");
    }

    public void updateCartItem(String name) {
        Session session = Session.getSession();
        Product product = searchItem(name);
        if (product == null) {
            System.out.println("Item does not exist");
            return;
        }
        int currentAmount = 0;

        for (cartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                currentAmount = cartProduct.getQuantity();
                System.out.println("Enter new amount: ");
                int amount = session.readInt();
                // if amount is more than current amount, remove from stock, else add to stock
                int addOrRemove = amount - currentAmount;

                while (addOrRemove > product.getStock()) {
                    System.out.println("Only " + product.getStock() + " of " + product.getName() + " available.");
                    System.out.println("Please enter amount within this range: ");
                    amount = session.readInt();
                    addOrRemove = amount - currentAmount;
                }

                if (addOrRemove > 0) {
                    product.setStock(product.getStock() - addOrRemove);
                    cartProduct.setQuantity(amount);
                } else {
                    product.setStock(product.getStock() - addOrRemove);
                    cartProduct.setQuantity(amount);
                }

                return;
            }
        }
        System.out.println("Item not in cart");
    }

    public void viewCart() {
        double totalPrice = 0;
        System.out.println("Your cart contains:");

        for (cartProduct product : inCart) {
            System.out.println("Name: " + product.getProduct().getName());
            System.out.println("Price: " + product.getProduct().getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println();

            totalPrice += product.getProduct().getPrice() * product.getQuantity();
        }
        System.out.println("Total price: " + totalPrice);
    }

    @Override
    public Product searchItem(String name) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    public void addingMenu() {
        Session session = Session.getSession();
        boolean back = false;
        do {
            System.out.println("1. Add item to cart    2. Back");
            int choice2 = session.readInt();
            switch (choice2) {
                case 1:
                    System.out.println("Enter name of item to add to cart: ");
                    String name = session.scanString.nextLine();

                    addToCart(name);
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
        // viewCart();
    }

    @Override
    public void handleActions() {
        Session session = Session.getSession();
        do {
            System.out.println(
                    "1. View all items    2. View by Galaxy    3. View by Planet    4. View items in cart    5. Log out");
            int choice = session.readInt();
            switch (choice) {
                case 1:
                    Store.viewAllProducts();
                    addingMenu();
                    break;
                case 2:
                    boolean galaxyExists = false;
                    String galaxy;
                    do {
                        Store.displayGalaxyNames();
                        System.out.println("Choose a galaxy: ");
                        galaxy = session.scanString.nextLine();
                        galaxyExists = Store.viewByGalaxy(galaxy);
                    }while(galaxyExists == false);
                    // only goes to add menu if galaxy exists
                    addingMenu();
                    break;
                case 3:
                    String planet = session.scanString.nextLine();
                    boolean planetExists = Store.viewByPlanet(planet);

                    do {
                        Store.displayPlanetNames();
                        System.out.println("Choose a planet: ");
                        planet = session.scanString.nextLine();
                        planetExists = Store.viewByPlanet(planet);
                    }while(planetExists == false);
                    // only goes to add menu if planet exists
                    addingMenu();
                    break;
                case 4:
                    boolean back2 = false;
                    do {
                        viewCart();

                        System.out.println("1. Remove item from cart    2. Update Item in cart    3. Back");
                        int choice3 = session.readInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter name of item to remove: ");
                                String name = session.scanString.nextLine();
                                removeFromCart(name);
                                break;
                            case 2:
                                System.out.println("Enter name of item to update: ");
                                String name2 = session.scanString.nextLine();
                                updateCartItem(name2);
                                break;
                            case 3:
                                back2 = true;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    } while (!back2);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        } while (true);
    }
}
