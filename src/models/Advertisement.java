package models;

public class Advertisement {
    private String brand;
    private String model;
    private String millage;
    private double price;
    private User owner;

    public Advertisement(String brand, String model, String millage, double price, User owner) {
        this.brand = brand;
        this.model = model;
        this.millage = millage;
        this.price = price;
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMillage() {
        return millage;
    }

    public void setMillage(String millage) {
        this.millage = millage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", millage='" + millage + '\'' +
                ", price=" + price +
                ", owner=" + owner +
                '}';
    }
}
