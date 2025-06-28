package Kiosk;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String menu;
    private int price;
    private List<String> toppings = new ArrayList<>();

    Order () {
        this.menu = "";
        this.price = 0;
    }

    // getter
    public String getMenu() {
        return menu;
    }
    public int getPrice() {
        return price;
    }
    public List<String> getTopping() {
        return toppings;
    }

    // setter
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void addTopping(String topping) {
        toppings.add(topping);
    }
    
    // 토핑 초기화
    public void resetTopping() {
        toppings.clear();
    }
}
