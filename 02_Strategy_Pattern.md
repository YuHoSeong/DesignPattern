## Java Design Pattern

# 스트래티지 패턴 (Strategy Pattern)

정의 : 스트래티지 패턴(Strategy Pattern)에서는 알고리즘군을 정의하고 각각을 캡슐화하여 교환해서 사용할 수 있도록 만든다. 스트래티지를 활용하면 알고리즘을 사용하는 클라이어트와는 독립적으로 알고리즘을 변경할 수 있다.

이해 : 어떤 하나의 기능을 구성하는 특정 부분을 실행중에 다른것으로 효과적으로 변경할 수 있는 방안을 제공한다.
SumPrinter 클래스의 print 메서드 매개변수를 SumStrategy interface로 넣어 원하는 Strategy.class를 구현해 변경할 수 있다.

## 예문

*SumPrinter.java*

```
public class SumPrinter {
    void print(SumStrategy strategy, int N){
        System.out.printf("The Sum of 1 - %d : ", N);
        System.out.println(strategy.get(N));
    }
}
```

*SumStrategy.java*

```
public interface SumStrategy {
    int get(int N);   
}
```

*SimpleSumStrategy.java*

```
public class SimpleSumStrategy implements SumStrategy {

    @Override
    public int get(int N) {
        int sum = N;

        for(long i=1; i<N; i++){
            sum += i;
        }

        return sum;
    }

}
```

*GaussSumStrategy.java*

```
public class GaussSumStrategy implements SumStrategy{

    @Override
    public int get(int N) {
        return (N+1)*N/2;
    }

}
```

*MainEntry.java*

```
public class MainEntry {
    public static void main(String[] args) {
        SumPrinter cal = new SumPrinter();

        cal.print(new SimpleSumStrategy(), 10);
        cal.print(new GaussSumStrategy(), 10);
    }
}
```