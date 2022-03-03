package AdapterPattern;

import java.util.ArrayList;

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