import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    private static Session session = null;
    public Scanner scanInt = new Scanner(System.in);
    public Scanner scanString = new Scanner(System.in);
    public Product [] initalProduct = new Product [12];
    public ArrayList<User> userList = new ArrayList<User>();

    private Session() {
        createDatabase();
    }

    private void createDatabase() {
        //todo add admin in user file
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("userListFile.bin"))){
            User admin = new Admin("admin1", 43, "Male", "admin1@gmail.com", "password");
            output.writeObject(admin);
            //todo add some default users
        } catch (Exception e) {
            System.out.println("Error");
        }

        //create some default user in file
        //todo create same product with condition type used and lower the price
        initalProduct[0] = new Product("Moon Rock Dust", 20, 5, "XVI", "A", "New");
        initalProduct[1] = new Product("Hydro-polymer Flux capacitor", 200, 5, "XVI", "A", "New");
        initalProduct[2] = new Product("Carbonised Panels", 100, 5, "V", "C", "New");
        initalProduct[3] = new Product("Cyro-Cooled Quantum Processor", 370, 5, "V", "C", "New");
        initalProduct[4] = new Product("Galactic Graviton Inductor", 560, 5, "IX", "D", "Used");
        initalProduct[5] = new Product("Xenon Gas-filled FUsion Core", 320, 5, "X", "B", "New");
        initalProduct[6] = new Product("Neutrino Amplifier Array", 490, 5, "II", "A", "New");
        initalProduct[7] = new Product("Anti-Matter Reactor Chamber", 780, 5, "VII", "F", "New");
        initalProduct[8] = new Product("Hyperluminal Data Crystal", 120, 5, "III", "A", "Used");
        initalProduct[9] = new Product("Quantum Entangled Transmitter", 490, 5, "II", "A", "New");
        initalProduct[10] = new Product("Hyperdimensional FLux capacitor", 490, 5, "II", "A", "New");
        initalProduct[11] = new Product("Dark Matter Injector", 490, 5, "II", "A", "New");

        for (Product product : initalProduct) {
            Store.getAllProducts().add(product);
        }

        //or write in file like this:
        //create a bin file with object of products
        //todo use append fileoutputstream for final part of project
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("productListFile.bin"));) {
            output.writeObject(initalProduct);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static Session getSession(){
        if(session == null){
            session = new Session();
        }
        return session;
    }
}
