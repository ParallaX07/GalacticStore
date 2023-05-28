/**
 * Utility class for loading and saving user and product data from/to files.
 */

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void loadUsersFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("userListFile.bin"))) {
            @SuppressWarnings("unchecked")
            ArrayList<User> loadedUsers = (ArrayList<User>) input.readObject();

            for (User user : loadedUsers) {
                Store.getAllUsers().add(user);
            }

        } catch (Exception e) {
            System.out.println("Loading users failed: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void saveUsersToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("userListFile.bin"))) {
            output.writeObject(Store.getAllUsers());
        } catch (Exception e) {
            System.out.println("Saving users failed: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void loadProductFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("productListFile.bin"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Product> loadedproducts = (ArrayList<Product>) input.readObject();
            for (Product product : loadedproducts) {
                Store.getAllProducts().add(product);
            }

        } catch (Exception e) {
            System.out.println("Loading users failed: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    public static void saveProductsToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("productListFile.bin"))) {
            output.writeObject(Store.getAllProducts());
        } catch (Exception e) {
            System.out.println("Saving products failed: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
