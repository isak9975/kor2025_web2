package example.자료저장;

import lombok.Value;

import java.util.*;

public class Example1 {
    public static void main(String[] args) {
        //-[생각] 이름: 유재석, 나이 : 40, 이름:신동엽, 나이:50
        // 조건1 : 위 4가지의 자료를 (자바) 저장/대입 하는 방법

        //1. 변수 : 하나의 자료를 저장하는 메모리 공간
        //[1] 자료가 4개 이니까 변수4개 만들자.
        //특징 : 자료 개수에 따라 변수가 많아지므로 관리가 힘들다.
        String name1 ="유재석"; //하나의 변수에는 하나의 자룔르 저장할 수 있다.
        int age1 = 40;
        String name2 ="신동엽";
        int age2 = 50;

        //2. 배열 : 동일한 타입의 여러 자료들을 하나의 변수에 저장하는 메모리 공간
        // 특징 : 파이썬 / JS 와 다르게 자바의 배열의 동일한 타입의 여러 자료 들,각 자료들 마다의 속성 정보를 알 수 없다. "유재석"이 이름인지 별명인지 모른다.
        //[2] 여러 자료들을 하나의 변수에 순서대로 저장하자.
        String[] array = {"유재석","40","신동엽","50"};

        //3. 클래스타입 : 서로 다른 타입의 여러 자료들을 하나의 자료로 저장하는 메모리 공간
        // 특징 : 자료들을 저장할때 자료들의 상징적인 이름 붙일 수 있다. 멤버변수
        ValueDto valueDto = new ValueDto();
        valueDto.name1 = "유재석";
        valueDto.age1 = 40;
        valueDto.name2 = "신동엽";
        valueDto.age2 = 50;

        //4. 컬렉션프레임워크<List,Set,Map> : 여러 자료들을 미리 만들어진 자료구조(클래스)에 따라 자료를 저장하는 메모리 공간
        //객체 처럼 만들지 않고 써도 된다.
        Map<String,String> valueMap  = new HashMap<>();
        valueMap.put("name1","유재석");
        valueMap.put("age1","40");
        valueMap.put("name2","신동엽");
        valueMap.put("age2","50");

        //List<String


        //[생각해보기2]
        //Remote 클래스에 Tv와 Audio 인터페이스 타입의 자료를 모두 저장할수 있도록 코드 수정하시오.
            //방법 : Tv 와 Audio 타입을 Remote 클래스가 구현 implements 한다.
//        Remo re = new Remote();
            // - implements : (여러개) 구현하다 [ 실무 ]
            // - exteds : (한개) 상속한다.

        //[생각해보기3]
        //ArrayList 타입의 자료와 LinkedList 타입의 자료를 하나의 타입으로 저장하는 방법
        //=> List 인터페이스 사용한다
    }
}//end c

interface Tv{}
interface Audio{}
//interface Remo implements Tv, Audio{} => 하나에 몰아줘서 그 하나만 implements 받는것도 가능하다.
class Remote implements Tv,Audio{
}

//3. 클래스 만들기
class ValueDto{
    String name1;
    int age1;
    String name2;
    int age2;
}
