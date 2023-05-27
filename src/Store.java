import java.util.ArrayList;

public class Store {
    //Session will write to file, and this will read from file
    //Every other class will deal with this, and at the end of the program, this will write to file

    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    private static ArrayList<String> allGalaxies = new ArrayList<String>();
    private static ArrayList<String> allPlanets = new ArrayList<String>();
    private static ArrayList<User> allUsers = new ArrayList<User>();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    
    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static void viewAllProducts(){
        System.out.println("All products:");
        for (Product product : allProducts) {
            System.out.println(product.toString());
            System.out.println();
        }
    }

    public static void displayGalaxyNames(){
        System.out.println("Galaxies:");
        //finds all unique galaxies from product list
        int i = 1;
        for (Product product : allProducts) {
            if (!allGalaxies.contains(product.getGalaxy())) {
                allGalaxies.add(product.getGalaxy());
            }
        }

        //prints all unique galaxies
        for (String galaxy : allGalaxies) {
            System.out.println(i + ": " + galaxy);
            i++;
        }
    }

    public static void displayPlanetNames(){
        System.out.println("Planets:");
        //finds all unique planets from product list
        int i = 1;
        for (Product product : allProducts) {
            if (!allPlanets.contains(product.getPlanet())) {
                allPlanets.add(product.getPlanet());
            }
        }

        //prints all unique planets
        for (String planet : allPlanets) {
            System.out.println(i + ": " + planet);
            i++;
        }
    }

    public static boolean viewByGalaxy(String galaxyName){
        System.out.println("Products in " + galaxyName + ":");
        boolean galaxyExists = false;

        for (Product product : allProducts) {
            if (product.getGalaxy().toLowerCase().equals(galaxyName.toLowerCase())) {
                System.out.println(product.toString());
                System.out.println();
                galaxyExists = true;
            }
        }
        if(!galaxyExists) {
            System.out.println("Galaxy does not exist in store.");
        }
        return galaxyExists;
    }

    public static boolean viewByPlanet(String planetName){
        System.out.println("Products in " + planetName + ":");
        boolean planetExists = false;
        for (Product product : allProducts) {
            if (product.getPlanet().toLowerCase().equals(planetName.toLowerCase())) {
                System.out.println(product.toString());
                System.out.println();
                planetExists = true;
            }
        }
        if(!planetExists) {
            System.out.println("Planet does not exist in store.");
        }
        return planetExists;
    }
}
