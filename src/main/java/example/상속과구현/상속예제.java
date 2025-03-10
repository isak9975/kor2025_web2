package example.상속과구현;

class Tire{
    public void roll(){
        System.out.println("일반 타이어가 회전합니다.");
    }
}

class Car{
    Tire tire; // Tire 타입으로 멤버변수 생성
    public void run(){
        this.tire.roll(); //Tire 타입의 roll 메소드 사용
    }
}

class KumhoTire extends Tire{//금호타이어는 일반타이어에게 상속 받는다
    @Override // extends 키워드 Tire 타입에게 물려받은 메소드를 재정의
    public void roll() {
        System.out.println("금호 타이어가 회전합니다.");
    }
}

class HankookTire extends Tire{
    @Override
    public void roll() {
        System.out.println("한국 타이어가 회전합니다.");
    }
}


public class 상속예제 {
    public static void main(String[] args) {

        //[1]일반 타이어 회전
        Car myCar = new Car();
//        myCar.run();
        //myCar.run(); //nullPointerException
        //즉] Car 객체는 존재하지만 Car 객체내 tire 객체가 존재하지 않으므로 오류 발생
        //myCar.run() --> tire.roll() 할 수 없다.

        //[2] 일반타잍어 회전
        Car yourCar = new Car();
        yourCar.tire = new Tire();
        yourCar.run(); //yourCar 객체내 Tire 객체를 대입 했으므로
        //즉 ] Car 객체는 존재하고 Car 객체내 tire객체가 존재하므로 오류가 없다

        //[3] 일반 타이어 회전
        myCar.tire = new Tire();
        myCar.run(); // myCar 객체내 Tire 객체를 대입 했으므로
        //즉] Car 객체는 존재하고 Car 객체 내 tire 객체가 존재하므로 오류가 없다.

        //[4] 금호 타이어 회전
        myCar.tire = new KumhoTire();
        myCar.run(); //myCar 객체내 KumhoTire 객체를 대입 했으므로
        //즉] Car 객체는 존재하고 Car 객체 내 kumhotire 객체가 존재하므로 오류가 없다

        //[5] 한국 타티어 회전
        myCar.tire = new HankookTire();
        myCar.run();//myCar 객체내 HankookTire 객체를 대입 했으므로
        //즉] Car 객체는 존재하고 Car 객체 내 HankookTire 객체가 존재하므로 오류가 없다

        //[6] 일반 타이어가 회전합
        yourCar.run(); // yourCar 객체는 tire 변경(대입)하지 않으므로 그대로 Tire 객체가 존재한다


        //결론
        //[1] 메소드 호출 하기 위해서는 인스턴스(new) 필요하다.
        //[2] Tire 타입에는 KumghoTire 와 HankookTire 가 대입된다. 용어 : 다형성(상속) 특징
    }
}