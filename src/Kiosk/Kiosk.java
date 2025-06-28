package Kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    public static void main(String[] args) {
        // 메뉴 init
        List<List<Beverage>> menus = Util.initMenu();
        // 주문 내역 list 선언
        List<Order> orders = new ArrayList<>();

        // 변수 선언
        Scanner sc = new Scanner(System.in);
        int orderNum = 1;   // 주문 번호
        int menuAnswer = 0;    // 메뉴 입력
        int itemAnswer = 0;     // 아이템 입력
        int toppingAnswer = 0;  // 토핑 입력
        int moretopping;    // 토핑 더 추가할 건지
        int checkOrder;     // 주문확정 여부
        int moreOrder;      // 더 주문할 건지
        int pay;            // 결제할 건지

        int orderCnt = 0; // 주문 개수
        int menuCnt = menus.size(); // 메뉴 개수
        int itemCnt;    // 아이템 개수
        int toppingCnt;

        // 새 주문 객체 생성
        orders.add(new Order());

        // 주문 시작
        while (true) {
            // 초기 화면
            if (menuAnswer == 0) {
                Util.printScreen();
                sc.nextLine();
            }


            // 카테고리 출력
            if (itemAnswer == 0) {
                for (int i = 0; i < 100; i++) System.out.println();
                Util.printCategory(menus);

                // 메뉴선택
                System.out.println("\n메뉴를 선택하세요\n'0'을 입력하면 이전으로 돌아갑니다.\n");
                menuAnswer = Util.select(sc, menuCnt);
            }
            if (menuAnswer == 0) {
                for (int i = 0; i < 50; i++) System.out.println();
                continue;
            }
            itemCnt = menus.get(menuAnswer - 1).size(); // 출력할 아이템 개수 결정


            // 메뉴(아이템) 출력
            if (toppingAnswer == 0) {
                for (int i = 0; i < 50; i++) System.out.println();
                System.out.printf("\n=== %s ===\n", menus.get(menuAnswer - 1).get(0).getSort());
                Util.printItems(menus.get(menuAnswer - 1));

                // 메뉴(아이템) 선택
                System.out.println("\n메뉴를 선택하세요\n'0'을 입력하면 이전으로 돌아갑니다.\n");
                itemAnswer = Util.select(sc, itemCnt);
            }
            if (itemAnswer == 0) continue;


            // 주문 내역 업데이트 (이름, 가격)
            orders.get(orderCnt).setMenu(menus.get(menuAnswer - 1).get(itemAnswer - 1).getName());
            orders.get(orderCnt).setPrice(menus.get(menuAnswer - 1).get(itemAnswer - 1).getPrice());
            System.out.println();


            // 빙수일 경우 토핑 고르기
            if (menus.get(menuAnswer - 1).get(itemAnswer - 1).getSort().equals("빙수")) {
                while (true) {
                    // 토핑 출력
                    for (int i = 0; i < 50; i++) System.out.println();
                    Util.printSelections((Bingsu) menus.get(0).get(itemAnswer - 1));
                    // 토핑 개수 결정
                    toppingCnt = ((Bingsu) menus.get(0).get(itemAnswer - 1)).getToppings().length;

                    // 토핑 선택
                    System.out.println("\n토핑을 선택하세요\n'0'을 입력하면 이전으로 돌아갑니다.\n");
                    toppingAnswer = Util.select(sc, toppingCnt);
                    if (toppingAnswer == 0) break;

                    // 주문 내역 업데이트 (토핑)
                    orders.get(orderCnt).addTopping(((Bingsu) menus.get(0).get(itemAnswer - 1)).getToppings()[toppingAnswer]);
                    
                    // 토핑 더 추가할지 선택
                    for (int i = 0; i < 50; i++) System.out.println();
                    System.out.printf("\n%s 토핑을 추가했습니다.\n", ((Bingsu) menus.get(0).get(itemAnswer - 1)).getToppings()[toppingAnswer]);
                    System.out.println("토핑을 추가하려면 '1'을, 선택을 마치려면 '0'을 입력하세요");
                    moretopping = Util.select0or1(sc);
                    if (moretopping == 1) continue;
                    else if (moretopping == 0) {
                        System.out.println();
                        break;
                    }
                }
                if (toppingAnswer == 0) {
                    // order 내역 초기화
                    orders.get(orderCnt).setMenu("");
                    orders.get(orderCnt).setPrice(0);
                    orders.get(orderCnt).resetTopping();
                    continue;
                }
            }


            // 주문 확정 선택
            for (int i = 0; i < 50; i++) System.out.println();
            Util.printItemOrder(orders.get(orderCnt));
            System.out.println("주문하시려면 '1', 취소하시려면 '0'을 눌러주세요.");
            checkOrder = Util.select0or1(sc);

            if (checkOrder == 0) continue;

            // 장바구니 출력
            for (int i = 0; i < 50; i++) System.out.println();
            System.out.println("\n- 장바구니에 메뉴가 담겼습니다. -");
            Util.printOrders(orders);


            // 더 주문할 건지 선택
            System.out.println("더 주문하시려면 '1', 주문을 완료하시려면 '0'을 입력해주세요");
            moreOrder = Util.select0or1(sc);

            // 더 주문하기
            if (moreOrder == 1) {
                toppingAnswer = 0;
                orders.add(new Order());
                orderCnt++;
                continue;
            }


            // 결제
            for (int i = 0; i < 50; i++) System.out.println();
            Util.printOrders(orders);
            Util.printTotalCharge(orders);

            System.out.println("\n결제하려면 '1', 이전 단계로 돌아가려면 '0'을 입력해주세요.");
            pay = Util.select0or1(sc);
            if (pay == 0) {
                toppingAnswer = 0;
                orders.add(new Order());
                orderCnt++;
                continue;
            }

            // 영수증 출력
            for (int i = 0; i < 50; i++) System.out.println();
            System.out.println("결제가 완료되었습니다.");
            System.out.println("영수증을 받아주세요.");
            Util.printReceipt(orders, orderNum);
            orderNum++;

            System.out.println("Enter키를 눌러 처음으로 돌아갑니다. (q)를 눌러 종료");
            String quit = sc.nextLine();
            if (quit.equalsIgnoreCase("q")) break;

            // 키오스크 초기화
            orders.clear();
            orders.add(new Order());
            menuAnswer = 0;
            itemAnswer = 0;
            toppingAnswer = 0;
            orderCnt = 0;
        }
    }
}
