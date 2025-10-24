package DAL;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class BasketItem {

    private static int count=1;
    private String name;
    private int quantity;
    private double price;
    private String category;
    private int id;

    public BasketItem() {
    }


    public BasketItem(int quantity, double price, String category, String name) {
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.name = name;
        id=count++;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }


    @NonNull
    @Override
    public String toString() {
        return String.format("%-28s %-20d %-6.2f", this.name, this.quantity, this.price);
    }
}
