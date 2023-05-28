/**
 * Represents an administrator in the system.
 * An admin can manage products and has all rights in the system.
 * This class extends from the User class and implements the Actions interface.
 */

class Admin extends User {
    public Admin(String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    private void addItem () {
        Session session = Session.getSession();
        System.out.println("Enter details of product to add:-");
        System.out.println("Enter name: ");
        System.out.print("> ");
        String name = session.scanString.nextLine();
        System.out.println("Enter price: ");
        double price = session.readDouble();
        System.out.println("Enter stock: ");
        int stock = session.readInt();
        System.out.println("Enter planet: ");
        System.out.print("> ");
        String planet = session.scanString.nextLine();
        System.out.println("Enter galaxy: ");
        System.out.print("> ");
        String galaxy = session.scanString.nextLine();
        System.out.println("Enter condition: ");
        System.out.print("> ");
        String condition = session.scanString.nextLine();
        Product product = new Product(name, price, stock, planet, galaxy, condition);

        if (Store.searchItem(name) != null) {
            Execution.clearConsole();
            System.out.println(product.getName() + " already in store.");
            return;
        }
        
        Store.getAllProducts().add(product);
        Execution.clearConsole();
        System.out.println("Product \"" + product.getName() + "\" added.");
    }

    private void removeItem (String name) {
        if (Store.searchItem(name) != null) {
            System.out.println("Product \"" + name + "\" removed");
            Store.getAllProducts().remove(Store.searchItem(name));
            return;
        }else 
            Execution.clearConsole();
            System.out.println("Product not found.");
    }

    @Override
    public void handleActions(){
        Session session = Session.getSession();
        do {
            System.out.println("1. View all products    2. Logout");
            int choice = session.readInt();
            switch(choice){
                case 1:
                Execution.clearConsole();
                Store.viewAllProducts();
                boolean back = false;
                do { 
                    
                    System.out.println("1. Add item    2. Remove item    3. Update item    4. Back");
                    int choice2 = session.readInt();
                    
                        switch(choice2){
                            case 1:
                                Execution.clearConsole();
                                addItem();
                                break;
                            case 2:
                                Execution.clearConsole();
                                Store.viewAllProducts();
                                System.out.println("Enter name of item you want to remove: ");
                                System.out.print("> ");
                                String name2 = session.scanString.nextLine();
                                Execution.clearConsole();
                                removeItem(name2);
                                break;
                            case 3:
                                Execution.clearConsole();
                                Store.viewAllProducts();
                                updateItem();
                                break;
                            case 4:
                                Execution.clearConsole();
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                            
                        }
                    } while (!back);
                    break;
                
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(true);
    }

    private void updateItem(){
        System.out.println("Enter name of product to update: ");
        System.out.print("> ");
        String name = Session.getSession().scanString.nextLine();
        Product product = Store.searchItem(name);

        Execution.clearConsole();
        //add tostring of product 
        System.out.println(product.toString());
        System.out.println();

        System.out.println("What do you want to update?");
        System.out.println("1. Name    2. Price    3. Stock    4. Planet    5. Galaxy    6. Condition");
        int choice = Session.getSession().readInt();

        switch(choice){
            case 1:
                Execution.clearConsole();
                System.out.println("Enter new name: ");
                System.out.print("> ");
                String newName = Session.getSession().scanString.nextLine();
                String oldName = product.getName();
                product.setName(newName);
                Execution.clearConsole();
                System.out.println("Product \"" + oldName + "\" updated to \"" + newName + "\"");
                break;
            case 2:
                Execution.clearConsole();
                System.out.println("Enter new price: ");
                double newPrice = Session.getSession().readDouble();
                double oldPrice = product.getPrice();
                product.setPrice(newPrice);
                Execution.clearConsole();
                System.out.println("Product \"" + product.getName() + "\" price updated from \"" + oldPrice + "\" to \"" + newPrice + "\"");
                break;
            case 3:
                Execution.clearConsole();
                System.out.println("Enter new stock: ");
                int newStock = Session.getSession().readInt();
                int oldStock = product.getStock();
                product.setStock(newStock);
                Execution.clearConsole();
                System.out.println("Product \"" + product.getName() + "\" stock updated from \"" + oldStock + "\" to \"" + newStock + "\"");
                break;
            case 4:
                Execution.clearConsole();
                System.out.println("Enter new planet: ");
                System.out.print("> ");
                String newPlanet = Session.getSession().scanString.nextLine();
                String oldPlanet = product.getPlanet();
                product.setPlanet(newPlanet);
                Execution.clearConsole();
                System.out.println("Product \"" + product.getName() + "\" planet updated from \"" + oldPlanet + "\" to \"" + newPlanet + "\"");
                break;
            case 5:
                Execution.clearConsole();
                System.out.println("Enter new galaxy: ");
                System.out.print("> ");
                String newGalaxy = Session.getSession().scanString.nextLine();
                String oldGalaxy = product.getGalaxy();
                product.setGalaxy(newGalaxy);
                Execution.clearConsole();
                System.out.println("Product \"" + product.getName() + "\" galaxy updated from \"" + oldGalaxy + "\" to \"" + newGalaxy + "\"");
                break;
            case 6:
                Execution.clearConsole();
                System.out.println("Enter new condition: ");
                System.out.print("> ");
                String newCondition = Session.getSession().scanString.nextLine();
                String oldCondition = product.getCondition();
                product.setCondition(newCondition);
                Execution.clearConsole();
                System.out.println("Product \"" + product.getName() + "\" condition updated from \"" + oldCondition + "\" to \"" + newCondition + "\"");
                break;
            default:
                Execution.clearConsole();
                System.out.println("Invalid choice");
                break;
        }

    }

}