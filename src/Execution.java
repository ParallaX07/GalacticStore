import java.io.*;

public class Execution {
    public static void main(String[] args) {

        Session session = Session.getSession();
        boolean exit = false;
        do {
            clearConsole();
            FileHandler.loadUsersFromFile();
            System.out.println("1. Login    2. Sign up    3. Exit Program");
            int choice = session.readInt();

            switch (choice) {
                case 1:
                    // ask for email and password
                    System.out.print("Email: ");
                    String email = session.scanString.nextLine();
                    System.out.println("Password: ");
                    String password = session.scanString.nextLine();

                    // add try catch to match email and password
                    try {
                        // call login method to match user
                        User loginUser = login(email, password);
                        System.out.println("Welcome " + loginUser.getName());
                        loginUser.handleActions();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    // call method to add user
                    signUp();
                    break;
                case 3:
                    FileHandler.saveUsersToFile();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!exit);

    }

    public static void signUp() {
        Session session = Session.getSession();
        int choice = -1;

        System.out.println("Enter details");
        boolean valid = false;
        String email;
        do{
            valid = true;
            System.out.print("Enter email: ");
            email = session.scanString.nextLine();
            if (!email.contains("@")) {
                valid = false;
                invalidInput();
                choice = session.readInt();
                while (choice != 0) {
                    invalidInput();
                    choice = session.readInt();
                }
                
            }
            
        }while(!valid);

        for (User user : Store.getAllUsers()) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) {
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

        System.out.print("Enter name: ");
        String name = session.scanString.nextLine();
        System.out.print("Enter age: ");
        int age = session.readInt();
        System.out.print("Enter gender: ");
        String gender = session.scanString.nextLine();
        
        System.out.print("Enter password: ");
        String password = session.scanString.nextLine();

        User customer = new Customer(name, age, gender, email, password);

        Store.getAllUsers().add(customer);
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
        System.out.println("Invalid input");
        System.out.println("Press 0 to go back");
    }

    // todo change void to User
    public static User login(String email, String password) throws Exception {
        // try (ObjectInputStream input = new ObjectInputStream(new
        // FileInputStream("userListFile.bin"))) {
        // User loginUser = (User)(input.readObject());
        // //match mail and pass with userListFile
        // } catch (Exception e) {
        // System.out.println("File not found");
        // }

        for (User user : Store.getAllUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful");
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
