package Kiosk;

public class Dessert extends Beverage {
    private static final String sort = "디저트";
    private static int count = 1;

    // 생성자
    public Dessert(String name, int price) {
        super(name, price, count);
        count++;
    }

    // getter
    @Override
    public String getSort() {
        return sort;
    }
}
