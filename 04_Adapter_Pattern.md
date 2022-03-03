## Java Design Pattern

# 어댑터 패턴 (Adapter Pattern)
> 연관성 없는 두 객체 묶어 사용하기
한 클래스의 인터페이스를 사용하고자 하는 *다른 인터페이스로 변환할 때* 주로 사용하며, 이를 이용하면 인터페이스 호환성이 맞지 않아 같이 쓸 수 없는 클래스를 연관 관계로 연결해서 사용할 수 있게하는 패턴



*어댑터 패턴의 장점*

관계가 없는 인터페이스 간 같이 사용 가능

프로그램 검사 용이

클래스 재활용성 증가


## 예문

*Animal.java*
```java
public abstract class Animal {
    protected String name;

    public Animal(String name){
        this.name = name;
    }

    public abstract void sound();
    
}
```

*Dog.java*
```java
public class Dog extends Animal{
    public Dog(String name){
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " Barking");
    }
    
}
```

*Cat.java*
```java
public class Cat extends Animal{
    public Cat(String name){
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " Meow");
    }

}
```

*Tiger.java*
```java
public class Tiger {
    private String name;

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    void roar(){
        System.out.println("growl");
    }
    
}
```

*TigerAdapter.java*
```java
public class TigerAdapter extends Animal{
    private Tiger tiger;

    public TigerAdapter(String name){
        super(name);

        tiger = new Tiger();
        tiger.setName(name);
    }

    @Override
    public void sound() {
        System.out.print(tiger.getName() + " ");
        tiger.roar();
    }
    
}
```

*User.java*
```java
public class User {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog("댕이"));
        animals.add(new Cat("솜톨이"));
        animals.add(new Cat("츄츄"));
        // animals.add(new Tiger("타이온"));
        animals.add(new TigerAdapter("타이온"));

        animals.forEach(animal ->{
            animal.sound();;
        });
    }   
}
/* 출력
댕이 Barking
솜톨이 Meow
츄츄 Meow
타이온 growl
*/
```