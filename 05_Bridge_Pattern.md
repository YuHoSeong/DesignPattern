## Java Design Pattern

# 브릿지 패턴 (Bridge Pattern)

기능 계층과 구현 계층의 분리

어댑터패턴과 흡사하다



## 예문

구현계층에서 하나의 데이터를 여러형태로 출력 (CaptionDisplay, SimpleDisplay)
기능계층에서 새로운 요구사항이 발생했을 때 기존에 작성된 코드를 변경하지 않고 새로운 기능을 추가(Publication)

*Draft.java*
```java
public class Draft {
    // 필드 추가
    private String title;
    private String author;
    private String[] content;

    // 접근자(Getter)
    public String getTitle(){ return title;}
    public String getAuthor(){ return author;}
    public String[] getContent(){ return content;}

    // 생성자
    public Draft(String title, String author, String[] content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

    // 재목,저자,내용을 출력하는 매서드
    public void print(Display display){
        display.title(this);
        display.author(this);
        display.content(this);
    }
    
}
```

*Display.java*
```java
public interface Display {
    void title(Draft draft);
    void author(Draft draft);
    void content(Draft draft);
    
}
```

*SimpleDisplay.java*
```java
// 구현계층(A)
public class SimpleDisplay implements Display{

    @Override
    public void title(Draft draft) {
        System.out.println(draft.getTitle());
    }

    @Override
    public void author(Draft draft) {
        System.out.println(draft.getAuthor());
    }

    @Override
    public void content(Draft draft) {
        String[] content = draft.getContent();
        for(int i=0; i<content.length; i++){
            System.out.println(content[i]);

        }
    }
    
}
```

*CaptionDisplay*
```java
// 구현계층(B)
public class CaptionDisplay implements Display{

    @Override
    public void title(Draft draft) {
        System.out.println("제목 : " + draft.getTitle());
    }

    @Override
    public void author(Draft draft) {
        System.out.println("저자 : "+ draft.getAuthor());        
    }

    @Override
    public void content(Draft draft) {
        String[] content = draft.getContent();
        System.out.println("내용 : ");
        for(int i=0; i<content.length; i++){
            System.out.println(content[i]);
        }
    }
    
}
```

*Publication.java*
```java
// 새로운 기능 추가 (출판될 경우 출판사와 가격을 추가)
public class Publication extends Draft{
    private String publisher;
    private int cost;

    public Publication(String title, String author, String[] content, String publisher, int cost){
        super(title, author, content);
        this.publisher = publisher;
        this.cost = cost;
    }

    private void printPublicationInfo(){
        System.out.println("#" + publisher + "$" + cost);
    }

    public void print(Display display){
        super.print(display);
        printPublicationInfo();
    }
}
```

*MainEntry.java*
```java
public class MainEntry {
    public static void main(String[] args) {
        String title = "디자인 패턴";
        String author = "유호성";
        String[] content = {"브릿지 패턴","bridge pattern"};

        Draft draft = new Draft(title, author, content);

        Display display1 = new SimpleDisplay();
        // draft.print(display1);
        /* 출력
        디자인 패턴
        유호성
        브릿지 패턴
        bridge pattern
        */
        Display display2 = new CaptionDisplay();
        // draft.print(display2);
        /* 출력
        제목 : 디자인 패턴
        저자 : 유호성
        내용 :
        브릿지 패턴
        bridge pattern
        */
        String publisher = "출판사";
        int cost = 100;

        Publication publication = new Publication(title, author, content, publisher, cost);

        publication.print(display2);
        /* 출력
        제목 : 디자인 패턴
        저자 : 유호성
        내용 :
        브릿지 패턴
        bridge pattern
        #출판사$100     <- 추가된 내용
        */
    }
}

```