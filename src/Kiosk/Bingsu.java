package Kiosk;

public class Bingsu extends Beverage {
    private static final String sort = "빙수";
    private static int count = 1;
    private String[] toppings;

    // 생성자
    public Bingsu(String name, int price, String[] toppings) {
        super(name, price, count);
        this.toppings = toppings;
        count++;
    }

    // getter
    public String[] getToppings() {
        return toppings;
    }

    @Override
    public String getSort() {
        return sort;
    }

}
