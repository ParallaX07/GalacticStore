import java.io.*;

public class Execution {
    public static void main(String[] args) {
        
        Session session = Session.getSession();

        //todo remove. Used only for test
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("productListFile.bin"))) {
            Product [] newProductList = (Product [])(input.readObject());
            System.out.println(newProductList.length);
        } catch (Exception e) {
            System.out.println("File not found");
        }

        while(true){
            // ask for email and password
            System.out.println("Email: ");
            String email = session.scanString.nextLine();
            System.out.println("Password: ");
            String password = session.scanString.nextLine();

            //add try catch to match email and password
            try {
                //call login method to match user
                User loginUser;
                
            } catch (Exception e) {
                System.out.println("User not found");
            }
        }
    }

    //todo change void to User
    public static void login(String email, String password) {
        Session session = Session.getSession();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("userListFile.bin"))) {
           User loginUser = (User)(input.readObject());
           //match mail and pass with userListFile
        } catch (Exception e) {
            System.out.println("File not found");
        }

    }
}
