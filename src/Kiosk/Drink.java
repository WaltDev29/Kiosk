package Kiosk;

public class Drink extends Beverage {
    private static final String sort = "음료";
    private static int count = 1;


    // 생성자
    public Drink(String name, int price) {
        super(name, price, count);
        count++;
    }

    // getter
    @Override
    public String getSort() {
        return sort;
    }
}
