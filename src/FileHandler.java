import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void loadUsersFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("userListFile.bin"))) {
            @SuppressWarnings("unchecked")
            ArrayList<User> loadedUsers = (ArrayList<User>) input.readObject();
            Store.setAllUsers(loadedUsers);
        } catch (Exception e) {
            System.out.println("Loading users failed");
        }
    }

    public static void saveUsersToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("userListFile.bin"))) {
            output.writeObject(Store.getAllUsers());
        } catch (Exception e) {
            System.out.println("Saving users failed");
            System.out.println(e.getStackTrace());
        }
    }
    
    public static void saveProductsToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("productListFile.bin"))) {
            output.writeObject(Store.getAllProducts());
            output.close();
        } catch (Exception e) {
            System.out.println("Saving products failed");
        }
    }
}
