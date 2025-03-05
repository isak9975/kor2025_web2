package example;

import java.util.ArrayList;
import java.util.List;
/*
    자바의 상속
        - 기존 클래스(부모)의 멤버변수 와 메소드를 새로운 클래스(자식)에게 물려(연장하다)주는 기능
        - 핵심 : 1. 클래스의 재사용, 2. 유지보수 용이, 3. **다형성**
        - 자식클래스명 extends 부모클래스명{ }
    자바는 100% 객체지향 이면서 다형성 특징 갖는다.
        -근거 : 자바의 최상위 클래스는 Object 클래스가 존재하낟.
        즉] 모든 클래스(+라이브러리) 는 Object 로 부터 상속을 자동으로 받는다. 100% 상속

        다형성 : 다양한 형태(모양/타입) 성질
            즉] Integer -> double -> Byte 타입 변환이 되는 과정
            -기본타입 : 기본타입변환
            -참조타입 :
                1. 강제타입변환(캐스팅) : 부모타입이 자식타입 변환 타입   (*처음 객체 생성될때 타입이면 가능*)
                    (1) Animal ->
                    (2) Dog ->
                2. 자동타입변환 : 자식타입이 부모타입 변환               (자식이 부모로부터 extends 했을 경우)

 */

class Animal{//동물

}
class Dog extends Animal{//강아지 //상속받음.

}
class Cat extends Animal{//고양이 //상속받음.

}

//==============================================================================//

public class Task1 {
    public static void main(String[] args) {
        Animal animal = new Animal(); // 동물 객체
        Dog dog = new Dog(); // 강아지 객체
        Cat cat = new Cat(); // 고양이 객체

        List<Animal> list1 =new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        //고민1 : 아래에 List 객체에 위의 3개 객체(동물, 강아지, 고양이)를 모두 대입할수 있는 방법(2가지 이상)
            //[1] : Object 타입 할 경우에는 Animal/Dog/Cat 타입 모두 대응할 수 있다.
            //[2] : Animal 타입 할 경우네는 Animal/dog/cat 모두 대응할 수있다.
            //[*] : Dog/Cat 타입 할 경우에는 상위타입 또는 형제타입를 대응할 수 없다.



        list1.add(dog);
        list1.add(cat);
        list1.add(animal);

        list2.add(dog);
        list2.add(cat);
        list2.add(animal);


        method1(dog);
        method1(cat);
        method1(animal); //animal : 인수? O / 매개변수? X

        method2(dog);
        method2(cat);
        method2(animal);
    }

    //고민2 : 메소드 매개변수에 3개 객체를 모두 대입할 수 있는 방법(2가지 이상)
    public static void method1(Animal animal){ //animal : 인수X / 매개변수 O

    }
    public static void method2(Object object){

    }

    //용어고민3?
        // 1. 인자/인수 : 함수로 들어가는 값 그자체 / 2.매개변수 : 함수로 들어오는 인수/값을 저장하는 메모리공간,(중)매 : 인자값과 변수 연결
        // 3. 변수 : 데이터를 저장할수 있는 메모리공간 / 4.리터럴 : 값 그자체
        // 5. 타입/자료형 : 값을 표현할 수 있는 형태 /모양 /형식  6. 다형성 : 타입변환





}
