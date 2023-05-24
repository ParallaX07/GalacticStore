package GalacticStore;

import java.util.Scanner;

public class Session {
    private static Session session = null;
    public Scanner scanInt = new Scanner(System.in);
    public Scanner scanString = new Scanner(System.in);

    private Session() {
        createDatabase();
    }

    private void createDatabase(){
        //add admin 
        //create some default user
        //create a bin file with object of products
    }

    public static Session getSession(){
        if(session == null){
            session = new Session();
        }
        return session;
    }
}
