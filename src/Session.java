import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Session {
    private static Session session = null;
    public Scanner scanInt = new Scanner(System.in);
    public int readInt() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = this.scanInt.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                this.scanInt.nextLine(); // discard the invalid input
            }
        }
        this.scanInt.nextLine();  // clear the newline left by nextInt()
        return choice;
    }

    public double readDouble() {
        double choice = -1;
        while (choice == -1) {
            try {
                choice = this.scanInt.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                this.scanInt.nextLine(); // discard the invalid input
            }
        }
        this.scanInt.nextLine();  // clear the newline left by nextInt()
        return choice;
    }

    public Scanner scanString = new Scanner(System.in);
    public Product [] initalProduct = new Product [12];
    public ArrayList<User> userList = new ArrayList<User>();

    private Session() {
        createDatabase();
    }

    private void createDatabase() {
        //todo add admin in user file
        userList.add(new Admin("admin", 43, "Male", "admin@gmail.com", "password"));
        userList.add(new Customer("customer1", 30, "Female", "customer1@gmail.com", "password"));
        userList.add(new Customer("customer2", 345, "Male", "customer2@gmail.com", "password"));
        
        //checks if any users were loaded from file to userList
        if(Store.getAllUsers().size() < 1){
            for (User user : userList) {
                Store.getAllUsers().add(user);
            }
        }

        //create some default user in file
        //todo create same product with condition type used and lower the price
        initalProduct[0] = new Product("Moon Rock Dust", 20, 5, "XVI", "Milky Way", "New");
        initalProduct[1] = new Product("Hydro-polymer Flux capacitor", 200, 5, "XVI", "Whirlpool", "New");
        initalProduct[2] = new Product("Carbonised Panels", 100, 5, "V", "Milky Way", "New");
        initalProduct[3] = new Product("Cyro-Cooled Quantum Processor", 370, 5, "V", "Whirlpool", "New");
        initalProduct[4] = new Product("Galactic Graviton Inductor", 560, 5, "IX", "Andromeda", "Used");
        initalProduct[5] = new Product("Xenon Gas-filled Fusion Core", 320, 5, "X", "Whirlpool", "New");
        initalProduct[6] = new Product("Neutrino Amplifier Array", 490, 5, "II", "Andromeda", "New");
        initalProduct[7] = new Product("Anti-Matter Reactor Chamber", 780, 5, "VII", "Andromeda", "New");
        initalProduct[8] = new Product("Hyperluminal Data Crystal", 120, 5, "III", "Whirlpool", "Used");
        initalProduct[9] = new Product("Quantum Entangled Transmitter", 490, 5, "II", "Andromeda", "New");
        initalProduct[10] = new Product("Hyperdimensional FLux capacitor", 490, 5, "II", "Milky Way", "New");
        initalProduct[11] = new Product("Dark Matter Injector", 490, 5, "II", "Andromeda", "New");

        //checks if any products were loaded from file to productLists
        if(Store.getAllProducts().size() < 1){
            for (Product product : initalProduct) {
                Store.getAllProducts().add(product);
            }
        }

    }

    public static Session getSession(){
        if(session == null){
            session = new Session();
        }
        return session;
    }
}