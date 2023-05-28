/**
 * This class starts the program, creates an instance of Session.
 */

public class Execution {
    public static void main(String[] args) {

        FileHandler.loadUsersFromFile();
        FileHandler.loadProductFromFile();
        Session session = Session.getSession();
        boolean exit = false;

        do {
            clearConsole();
            // System.out.println("Users: " + Store.getAllUsers().size());
            // System.out.println("Products: " + Store.getAllProducts().size());
            System.out.println("1. Login    2. Sign up    3. Exit Program");
            int choice = session.readInt();

            switch (choice) {
                case 1:
                    // ask for email and password
                    clearConsole();
                    System.out.print("Email: ");
                    String email = session.scanString.nextLine();
                    System.out.print("Password: ");
                    String password = session.scanString.nextLine();

                    // add try catch to match email and password
                    try {
                    // call login method to match user
                    User loginUser = login(email, password);
                    System.out.println("Welcome " + loginUser.getName() + " to the Galactic Store!");
                    loginUser.handleActions();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Press 0 to go back");
                        session.readInt();
                    }
                    break;

                case 2:
                    clearConsole();
                    // call method to add user
                    signUp();
                    break;
                case 3:
                    FileHandler.saveUsersToFile();
                    FileHandler.saveProductsToFile();
                    exit = true;
                    break;
                default:
                    clearConsole();
                    System.out.println("Invalid input");
                    break;
            }
        } while (!exit);

    }

    public static void signUp() {
        Session session = Session.getSession();
        int choice = -1;

        boolean valid = false;
        String email;
        do{
            clearConsole();
            valid = true;
            System.out.println("Enter details:-");
            System.out.print("Enter email: ");
            email = session.scanString.nextLine();
            if (!email.contains("@")) {
                valid = false;
                invalidInput();
                choice = session.readInt();
                while (choice != 0) {
                    clearConsole();
                    invalidInput();
                    choice = session.readInt();
                }
                
            }
            
        }while(!valid);

        for (User user : Store.getAllUsers()) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                clearConsole();
                System.out.println("Already registered");
                System.out.println("Press 0 to go back");
                choice = session.readInt();
        
                while (choice != 0) {
                    invalidInput();
                    choice = session.readInt();
                }
                return;
            }
        }

        System.out.println("Enter name: ");
        System.out.print("> ");
        String name = session.scanString.nextLine();
        System.out.println("Enter age: ");
        int age = session.readInt();
        System.out.println("Enter gender: ");
        System.out.print("> ");
        String gender = session.scanString.nextLine();
        
        System.out.println("Enter password: ");
        System.out.print("> ");
        String password = session.scanString.nextLine();

        User customer = new Customer(name, age, gender, email, password);

        Store.getAllUsers().add(customer);
        clearConsole();
        System.out.println("Sign up successful!");
        System.out.println("Press 0 to go back");
        choice = session.readInt();

        while (choice != 0) {
            invalidInput();
            choice = session.readInt();
        }
        return;
    }

    public static void invalidInput() {
        clearConsole();
        System.out.println("Invalid input");
        System.out.println("Press 0 to go back");
    }

    public static User login(String email, String password) throws Exception {
        for (User user : Store.getAllUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                clearConsole();
                System.out.println("Login successful!");
                return user;
            }
        }
        clearConsole();
        throw new Exception("User not found");
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
