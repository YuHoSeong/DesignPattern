## Java Design Pattern

# 이터레이터 패턴 (Iterator Pattern)

정의 : Iterator패턴이란, 무엇인가 많이 모여있는 것들을 순서대로 지정하면서 전체를 검색하는 처리를 실행하기 위한 것입니다. (Iterator : 반복하다 , 반복자)

이해 : 동일한 형태의 데이터 항목을 여러 개 가지고 있는 것을 Container 또는 Aggregator 라고 칭한다.
Aggregator의 구성 데이터를 하나씩 추출해야 할때 Aggregator의 종류(Array,Set 등)에 따라 방법이 다르다.
이를 통일된 방법으로 가져오기 위해 사용할 수 있는 패턴이 Iterator패턴이다.

## 예문

*Aggregator.java*

```
public interface Aggregator {
    Iterator iterator();
}
```

*Iterator.java*

```
public interface Iterator {
    boolean next();
    Object current();
}
```

*Array.java*

```
public class Array implements Aggregator {
    private Item[] items;

    public Array(Item[] items){
        this.items = items;
    }

    public Item getItem(int index){
        return items[index];
    }

    public int getCount(){
        return items.length;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(this);
    }

}
```

*ArrayIterator.java*

```
public class ArrayIterator implements Iterator{
    private Array array;
    private int index;

    public ArrayIterator(Array array){
        this.array = array;
        this.index = -1;
    }

    @Override
    public boolean next() {
        index++;
        return index < array.getCount();
    }

    @Override
    public Object current() {
        return array.getItem(index);
    }

}
```

*Item.java*

```
public class Item {
    private String name;
    private int cost;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "(" + name + "," + cost + ")";
    }

}
```

*MinaEntry.java*

```
public class MainEntry {
    public static void main(String[] args) {
        Item[] items = {
            new Item("CPU", 1000),
            new Item("Keyboard", 2000),
            new Item("Mouse", 3000),
            new Item("HDD", 50)
        };

        Array array = new Array(items);
        Iterator it = array.iterator();

        while(it.next()){
            Item item = (Item)it.current();
            System.out.println(item);
        }
    }

}
```