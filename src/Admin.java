class Admin extends User{

    public Admin(String name, String age, String gender, String email, String password) {
        super(name, age, gender, email, password);
    }

    public void addItem (Product product) {
        if (Store.getAllProducts().contains(product)) {
            System.out.println(product.getName() + " already in store");
            return;
        }
        Store.getAllProducts().add(product);
    }

    public void removeItem (Product product) {
        if (Store.getAllProducts().contains(product)) {
            Store.getAllProducts().remove(product);
        }
    }

}