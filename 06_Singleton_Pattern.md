## Java Design Pattern

# 싱글톤 패턴 (Singleton Pattern)

>  ***하나의 인스턴스만 있도록 하기***


객체란?

객체: 속성과 기능을 갖춘 것

클래스: 속성과 기능을 정의한 것, 

인스턴스: 속성과 기능을 가진 것중 실제한 것


## 예문

*King.java*
```java
public class King {
    // 다른 곳에서 생성자를 호출 할 수 없도록 private으로 지정
    private King() {};

    // 자기 자신만이 이 필드에 접근가능하도록 private 지정
    private static King self = null;

    // synchronized : 멀티쓰레드에서 호출할때 문제 없도록 동기화 하기 위함
    public synchronized static King getInstance(){
        if(self == null){
            self = new King();
        }
        return self;
    }
    
    public void say(){
        System.out.println("I am only one..");
    }
}
```
