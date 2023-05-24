import java.io.Serializable;

public class Product implements Serializable{
    private String name;
    private double price;
    private int stock;
    private int planet;
    private String galaxy;
    private String condition;

    private int cartQuantity;


    public Product(String name, double price, int stock, int planet, String galaxy, String condition) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.planet = planet;
        this.galaxy = galaxy;
        this.condition = condition;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPlanet() {
        return this.planet;
    }

    public void setPlanet(int planet) {
        this.planet = planet;
    }

    public String getGalaxy() {
        return this.galaxy;
    }

    public void setGalaxy(String galaxy) {
        this.galaxy = galaxy;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getCartQuantity() {
        return this.cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

}