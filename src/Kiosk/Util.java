package Kiosk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Util {

    // 객체 생성 메서드
    // 메뉴 리스트 생성 메서드
    static List<List<Beverage>> initMenu() {
        List<List<Beverage>> menus = new ArrayList<>();
        Util.initBingsu(menus);
        Util.initDrink(menus);
        Util.initDessert(menus);
        return menus;
    }
    // 빙수
    static void initBingsu(List<List<Beverage>> menus) {
        List<Beverage> bingsus = new ArrayList<>(List.of(
                new Bingsu("팥빙수", 9900, new String[]{"팥","인절미","연유","아몬드"}),
                new Bingsu("망고빙수", 11900, new String[]{"망고","망고시럽","연유"}),
                new Bingsu("인절미빙수", 10900, new String[]{"인절미","콩가루","연유"}),
                new Bingsu("초코빙수", 12900, new String[]{"초코칩","초코시럽","오레오","휘핑크림","연유"}),
                new Bingsu("딸기빙수", 12900, new String[]{"딸기","연유"})
        ));
        menus.add(bingsus);
    }
    // 음료
    static void initDrink(List<List<Beverage>> menus) {
        List<Beverage> drinks = new ArrayList<>(List.of(
                new Drink("아메리카노", 3000),
                new Drink("라떼", 4100),
                new Drink("밀크티", 4200),
                new Drink("에이드", 4000),
                new Drink("녹차", 3500)
        ));
        menus.add(drinks);
    }
    // 디저트
    static void initDessert(List<List<Beverage>> menus) {
        List<Beverage> desserts = new ArrayList<>(List.of(
                new Dessert("조각 케이크", 5200),
                new Dessert("브라우니", 4800),
                new Dessert("마카롱", 2000),
                new Dessert("크루아상", 4500),
                new Dessert("토스트", 3900)
        ));
        menus.add(desserts);
    }

    // 키오스크 화면 출력 메서드
    // 초기 화면
    static void printScreen() {
        System.out.println("\n*** 설빙 ***");
        System.out.println("\nEnter키를 주문을 시작하세요.");
    }
    // 메뉴 카테고리 화면
    static void printCategory(List<List<Beverage>> menus) {
        int i = 1;
        System.out.println("=== 메뉴 ===");
        for (List<Beverage> b : menus) {
            System.out.printf("%d. %s\n", i ,b.get(0).getSort());
            i++;
        }
    }
    // 메뉴 아이템 화면
    static void printItems(List<Beverage> items) {
        for (Beverage b : items) {
            System.out.printf("%d. %-8s %5d원\n", b.getNum(), b.getName(), b.getPrice());
        }
    }
    // 토핑 선택 화면
    static void printSelections(Bingsu bing) {
        System.out.printf("%-8s %5d원\n", bing.getName(), bing.getPrice());

        System.out.println("\n=== 토핑 (무료) ===");
        int i = 1;
        for (String s : bing.getToppings()) {
            System.out.printf("%d. %s \n", i, s);
            i++;
        }
    }
    // 현재 order 정보 출력
    static void printItemStat(Order o) {
        System.out.printf("%-8s %5d원\n", o.getMenu(), o.getPrice());
        if (!o.getTopping().isEmpty()) {
            System.out.println(" 토핑 : " + o.getTopping());
        }
        System.out.println();
    }
    // 장바구니에 담을 아이템 정보 출력
    static void printItemOrder(Order o) {
        System.out.println("*** 상품정보 ***");
        printItemStat(o);
    }
    // 주문 내역 출력
    static void printOrders(List<Order> orders) {
        int i = 1;
        System.out.println("\n**** 주문 내역 ****\n");
        for (Order o : orders) {
//            System.out.printf("=== %d ===\n", i);
            System.out.printf("%d. ", i);
            printItemStat(o);
            i++;
        }
    }
    // 결제할 금액 출력
    static void printTotalCharge(List<Order> orders) {
        int total = 0;
        for (Order o : orders) {
            total += o.getPrice();
        }
        System.out.printf("결제 금액 : %d원\n", total);
    }
    // 영수증 출력
    static void printReceipt(List<Order> orders, int orderNum) {
        System.out.println("\n----- 영수증 -----");
        System.out.println("설빙 이태원점");
        System.out.printf("주문번호 : 100%d\n", orderNum);
        printOrders(orders);
        System.out.println("------------------");
        printTotalCharge(orders);
        System.out.println("------------------\n");
    }

    // 트라이 캐치
    // 리스트 중에 선택
    static int select(Scanner sc, int cnt) {
        int answer;
        while (true) {
            try {
                System.out.print("번호 입력 : ");
                answer = sc.nextInt();
                if (answer < 0 || answer > cnt) {
                    System.out.printf("0~%d의 숫자를 입력해주세요.\n'0'을 입력하면 이전으로 돌아갑니다.\n", cnt);
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n다시 입력해주세요.\n");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return answer;
    }
    // 0 혹은 1 선택
    static int select0or1(Scanner sc) {
        int answer;
        while (true) {
            try {
                System.out.print("번호 입력 : ");
                answer = sc.nextInt();
                if (answer < 0 || answer > 1) {
                    System.out.println("0 혹은 1을 입력해주세요.");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n다시 입력해주세요.\n");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return answer;
    }
}
