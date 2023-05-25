import java.io.*;
public class Execution {
    public static void main(String[] args) {
        
        Session session = Session.getSession();

        while(true){
            // ask for email and password
            System.out.println("Email: ");
            String email = session.scanString.nextLine();
            System.out.println("Password: ");
            String password = session.scanString.nextLine();

            //add try catch to match email and password
            try {
                //call login method to match user
                User loginUser = login(email, password);
                System.out.println("Welcome " + loginUser.getName());
                loginUser.handleActions();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    //todo change void to User
    public static User login(String email, String password) throws Exception{
        Session session = Session.getSession();

        // try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("userListFile.bin"))) {
        //    User loginUser = (User)(input.readObject());
        //    //match mail and pass with userListFile
        // } catch (Exception e) {
        //     System.out.println("File not found");
        // }

        for (User user : session.userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful");
                return user;
            }
        }
        
        throw new Exception("User not found");
    }
}
