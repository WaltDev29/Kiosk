package Kiosk;

public abstract class Beverage {
    private String name;
    private int price;
    private int num;

    // 생성자
    public Beverage(String name, int price, int num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public abstract String getSort();

    // getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }
}
