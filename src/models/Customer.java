package models;
/**
 * Represents a customer in the system.
 * A customer can browse and purchase products.
 * This class extends from the User class and implements the Actions interface.
 */

import java.util.ArrayList;

import controllers.Execution;
import controllers.Session;
import database.Store;


public class Customer extends User {
    private ArrayList<CartProduct> inCart = new ArrayList<CartProduct>();
    private ArrayList<CartProduct> orderHistory = new ArrayList<CartProduct>();

    public Customer(String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    private void addToCart(String name) {
        Session session = Session.getSession();
        Product product = Store.searchItem(name);
        int amount = 0;

        Execution.clearConsole();

        if (product == null) {
            System.out.println("Item does not exist");
            return;
        } else {
            if (product.getStock() == 0) {
                System.out.println(product.getName() + " is out of stock");
                return;
            }
            System.out.println("Enter amount of " + name + " to add: ");
            amount = session.readInt();
        }

        while (amount > product.getStock()) {
            Execution.clearConsole();
            System.out.println("Only " + product.getStock() + " of " + product.getName() + " available.");
            System.out.println("Please enter amount within this range: ");
            amount = session.readInt();
        }

        for (CartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                Execution.clearConsole();
                System.out.println("Item already in cart. Do you want to update the item? (y/n)");
                String choice = session.scanString.nextLine();
                if (choice.toLowerCase().equals("y")) {
                    updateCartItem(name);
                    return;
                }
                return;
            }
        }

        // change product incart quantity
        product.setStock(product.getStock() - amount);
        inCart.add(new CartProduct(product, amount));
        

    }

    private void removeFromCart(String name) {
        Product product = Store.searchItem(name);
        if (product == null) {
            System.out.println("Item does not exist");
            return;
        }
        for (CartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                inCart.remove(cartProduct);
                return;
            }
        }
        System.out.println("Item not in cart");
    }

    private void updateCartItem(String name) {
        Session session = Session.getSession();
        Product product = Store.searchItem(name);

        if (product == null) {
            Execution.clearConsole();
            System.out.println("Item does not exist");
            return;
        }
        int currentAmount = 0;

        for (CartProduct cartProduct : inCart) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                Execution.clearConsole();
                currentAmount = cartProduct.getQuantity();
                System.out.println("Enter new amount: ");
                int amount = session.readInt();
                if(amount == 0){
                    removeFromCart(name);
                    return;
                }
                // if amount is more than current amount, remove from stock, else add to stock
                int addOrRemove = amount - currentAmount;

                while (addOrRemove > product.getStock()) {
                    Execution.clearConsole();
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
        if (!inCart.isEmpty()) {
            System.out.println("Your cart contains:");
            for (CartProduct product : inCart) {
                System.out.println("Name: " + product.getProduct().getName());
                System.out.println("Price: " + product.getProduct().getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println();

                totalPrice += product.getProduct().getPrice() * product.getQuantity();
            }
            System.out.println("Total price: " + totalPrice);
        } else {
            System.out.println("Your cart is empty");
        }
    }

    private void viewOrderHistory(){
        if(!orderHistory.isEmpty()){
            System.out.println("Your order history contains:");
            for (CartProduct product : orderHistory) {
                System.out.println("Name: " + product.getProduct().getName());
                System.out.println("Price: " + product.getProduct().getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println();
                System.out.println();
            }
        }
        else{
            System.out.println("Your order history is empty");
        }
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
                    System.out.print("> ");
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
            Execution.clearConsole();
            System.out.println(
                    "1. View all items    2. View by Galaxy    3. View by Planet    4. View items in cart    5. View order History    6.Log out");
            int choice = session.readInt();
            switch (choice) {
                case 1:
                    Execution.clearConsole();
                    Store.viewAllProducts();
                    addingMenu();
                    break;
                case 2:
                boolean galaxyExists = false;
                String galaxy;
                do {
                        Execution.clearConsole();
                        Store.displayGalaxyNames();
                        System.out.println("Choose a galaxy: ");
                        System.out.print("> ");
                        galaxy = session.scanString.nextLine();
                        galaxyExists = Store.viewByGalaxy(galaxy);
                    }while(galaxyExists == false);
                    // only goes to add menu if galaxy exists
                    addingMenu();
                    break;
                case 3:
                String planet;
                boolean planetExists = false;
                
                do {
                        Execution.clearConsole();
                        Store.displayPlanetNames();
                        System.out.println("Choose a planet: ");
                        System.out.print("> ");
                        planet = session.scanString.nextLine();
                        planetExists = Store.viewByPlanet(planet);
                    }while(planetExists == false);
                    addingMenu();
                    break;
                case 4:
                boolean back2 = false;
                do {
                        Execution.clearConsole();
                        viewCart();

                        System.out.println("1. Remove item from cart    2. Update Item in cart    3. Confirm purchase    4.Back");
                        int choice3 = session.readInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter name of item to remove: ");
                                System.out.print("> ");
                                String name = session.scanString.nextLine();
                                removeFromCart(name);
                                break;
                            case 2:
                                System.out.println("Enter name of item to update: ");
                                System.out.print("> ");
                                String name2 = session.scanString.nextLine();
                                updateCartItem(name2);
                                break;
                            case 3:
                                for (CartProduct product : inCart) {
                                    orderHistory.add(product);
                                }
                                inCart.clear();
                                System.out.println("Purchase confirmed");
                                break;
                            case 4:
                                back2 = true;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    } while (!back2);
                    break;
                case 5:
                    Execution.clearConsole();
                    viewOrderHistory();
                    System.out.println("Press 0 to go back");
                    session.readInt();
                    break;
                case 6:
                    Execution.clearConsole();
                    return;
                default:
                    Execution.clearConsole();
                    System.out.println("Invalid input");
                    break;
            }

        } while (true);
    }
}
