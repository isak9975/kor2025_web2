package example.상속과구현;

interface Tire2{ //일반 타이어 인터페이스 선언
    void roll(); //추상메소드
}//f end

class Car2{ //자동차 클래스 선언
    Tire2 tire2; // 일반타이어 타입 멤버변수 선언
    public void roll(){tire2.roll();} //일반 타이어 타입으로 roll 메소드 호출
}

class HankookTire2 implements Tire2{
    //extends 상위클래스 : 상속 vs implements 인터페이스명 : 구현

    @Override
    public void roll() {
        System.out.println("한국 타이어가 회전합니다");
    }
}

class KumhoTire2 implements Tire2{
    @Override
    public void roll() {
        System.out.println("금호 타이어가 회전합니다");
    }
}

public class 구현예제 {
    public static void main(String[] args) {

        //[1] 추상메서드가 정의되지 않았기에 roll 메소드 실행불가.
        Car2 myCar = new Car2();
//        myCar.roll();

        //[2] 한국 타이어 회전
        Car2 yourCar = new Car2();
        yourCar.tire2 = new HankookTire2();
        yourCar.roll();

        //[3] 한국타이어 회전
        myCar.tire2 = new HankookTire2();
        myCar.roll();

        //[4] 금호타이어 회전
        myCar.tire2 = new KumhoTire2();
        myCar.roll();

        //[5] 한국타이어 회전
        yourCar.roll();
    }
}
