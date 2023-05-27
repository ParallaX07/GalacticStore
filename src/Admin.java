import java.util.ArrayList;

class Admin extends User  {
    ArrayList<Product> allProducts = Store.getAllProducts();
    public Admin(String name, int age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    public void addItem () {
        Session session = Session.getSession();
        System.out.println("Enter details of product to add");
        System.out.print("Enter name");
        String name = session.scanString.nextLine();
        System.out.println("Enter price");
        double price = session.readDouble();
        System.out.println("Enter stock");
        int stock = session.readInt();
        System.out.println("Enter planet");
        String planet = session.scanString.nextLine();
        System.out.println("Enter galaxy");
        String galaxy = session.scanString.nextLine();
        System.out.println("Enter condition");
        String condition = session.scanString.nextLine();
        Product product = new Product(name, price, stock, planet, galaxy, condition);

        if (searchItem(name) != null) {
            System.out.println(product.getName() + " already in store");
            return;
        }
        
        allProducts.add(product);
        System.out.println("Product \"" + product.getName() + "\" added");
    }

    @Override
    public Product searchItem(String name){
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    public void removeItem (String name) {
        if (searchItem(name) != null) {
            allProducts.remove(searchItem(name));
            return;
        }else 
            System.out.println("Product not found");
    }

    @Override
    public void handleActions(){
        Session session = Session.getSession();
        do {
            System.out.println("1. View all products    2. Logout");
            int choice = session.readInt();
            switch(choice){
                case 1:
                Store.viewAllProducts();
                boolean back = false;
                do { 
                    
                    System.out.println("1. Add item    2. Remove item    3. Update item    4. Back");
                    int choice2 = session.readInt();
                    
                        switch(choice2){
                            case 1:
                                addItem();
                                break;
                            case 2:
                                System.out.print("Enter name of item you want to remove: ");
                                String name2 = session.scanString.nextLine();
                                removeItem(name2);
                                System.out.println("Product \"" + name2 + "\" removed");
                                break;
                            case 3:
                                updateItem();
                                break;
                            case 4:
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

    public void updateItem(){
        System.out.println("Enter name of product to update: ");
        String name = Session.getSession().scanString.nextLine();
        Product product = searchItem(name);
        System.out.println("What do you want to update?");
        System.out.println("1. Name    2. Price    3. Stock    4. Planet    5. Galaxy    6. Condition");
        int choice = Session.getSession().readInt();
        switch(choice){
            case 1:
                System.out.println("Enter new name");
                String newName = Session.getSession().scanString.nextLine();
                String oldName = product.getName();
                product.setName(newName);
                System.out.println("Product \"" + oldName + "\" updated to \"" + newName + "\"");
                break;
            case 2:
                System.out.println("Enter new price");
                double newPrice = Session.getSession().readDouble();
                double oldPrice = product.getPrice();
                product.setPrice(newPrice);
                System.out.println("Product \"" + product.getName() + "\" price updated from \"" + oldPrice + "\" to \"" + newPrice + "\"");
                break;
            case 3:
                System.out.println("Enter new stock");
                int newStock = Session.getSession().readInt();
                int oldStock = product.getStock();
                product.setStock(newStock);
                System.out.println("Product \"" + product.getName() + "\" stock updated from \"" + oldStock + "\" to \"" + newStock + "\"");
                break;
            case 4:
                System.out.println("Enter new planet");
                String newPlanet = Session.getSession().scanString.nextLine();
                String oldPlanet = product.getPlanet();
                product.setPlanet(newPlanet);
                System.out.println("Product \"" + product.getName() + "\" planet updated from \"" + oldPlanet + "\" to \"" + newPlanet + "\"");
                break;
            case 5:
                System.out.println("Enter new galaxy");
                String newGalaxy = Session.getSession().scanString.nextLine();
                String oldGalaxy = product.getGalaxy();
                product.setGalaxy(newGalaxy);
                System.out.println("Product \"" + product.getName() + "\" galaxy updated from \"" + oldGalaxy + "\" to \"" + newGalaxy + "\"");
                break;
            case 6:
                System.out.println("Enter new condition");
                String newCondition = Session.getSession().scanString.nextLine();
                String oldCondition = product.getCondition();
                product.setCondition(newCondition);
                System.out.println("Product \"" + product.getName() + "\" condition updated from \"" + oldCondition + "\" to \"" + newCondition + "\"");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }

}