## Java Design Pattern



# 템플릿 메소드 패턴 (Template Method Pattern)

알고리즘의 **구조를 메소드에** **정의**하고, 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 **재정의** 하는 패턴



> 어떨때 적용할까?
>
> 구현하려는 알고리즘이 ***일정한 프로세스***가 있다. (여러 단계로 나눌 수 있다.)
>
> 구현하려는 알고리즘이 ***변경 가능성***이 있다. (변경 가능성이 크다.)


> 구현 방법
(1) 알고리즘을 여러 단계로 나눈다.

(2) 나눠진 알고리즘의 단계를 메소드로 선언한다.

(3) 알고리즘을 수행할 템플릿 메소드를 만든다.

(4) 하위 클래스에서 나눠진 메소드들을 구현한다.

## 예문 1

*Article.java*

```java
// Article 객체 구현
public class Article {
    private String title;
    private ArrayList<String> content;
    private String footer;

    public Article(String title, ArrayList<String> content, String footer){
        this.title = title;
        this.content = content;
        this.footer = footer;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public String getFooter(){
        return footer;
    }
    
}
```

*DisplayArticleTemple.java*
```java
// 추상클래스
// 템플릿 선언
public abstract class DisplayArticleTemplate {

    protected Article article;

    public DisplayArticleTemplate(Article article) {
        this.article = article;
    }

    public final void display(){
        title();
        content();
        footer();
    }

    protected abstract void title();
    protected abstract void content();
    protected abstract void footer();
    
}
```

*CaptionDisplayArticle.java*
```java
// A 템플릿 구현부
public class CaptionDisplayArticle extends DisplayArticleTemplate{

    public CaptionDisplayArticle(Article article){
        super(article);
    }

    @Override
    protected void title() {
        System.out.println("TITLE : "+article.getTitle());
    }

    @Override
    protected void content() {
        System.out.println("CONTENT : ");
        ArrayList<String> content = article.getContent();
        int cntLines = content.size();
        for(int i=0; i<cntLines; i++){
            System.out.println(content.get(i));
        }
    }

    @Override
    protected void footer() {
        System.out.println("FOOTER : "+article.getFooter());
    }
    
}
```

*SimpleDisplayArcticle.java*
```java
// B 템플릿 구현부
public class SimpleDisplayArcticle extends DisplayArticleTemplate{
    public SimpleDisplayArcticle(Article article){
        super(article);
    }

    @Override
    protected void title() {
        System.out.println(article.getTitle());
    }

    @Override
    protected void content() {
        ArrayList<String> content = article.getContent();
        int cntLines = content.size();
        for(int i=0; i<cntLines; i++){
            System.out.println(content.get(i));
        }
    }

    @Override
    protected void footer() {
        System.out.println(article.getFooter());
    }
    
}
```

*MainEntry.java*
```java
public class MainEntry {
    public static void main(String[] args) {
        String title = "디자인패턴";
        ArrayList<String> content = new ArrayList<String>();
        content.add("내용");
        String footer = "2022.03.03, 유호성";

        Article article = new Article(title, content, footer);

        System.out.println("[CASE -1]");
        DisplayArticleTemplate style1 = new SimpleDisplayArcticle(article);
        style1.display();

        System.out.println("[CASE-2]");
        DisplayArticleTemplate style2 = new CaptionDisplayArticle(article);
        style2.display();
    }
}
/* 출력
[CASE -1]
디자인패턴
내용
2022.03.03, 유호성
[CASE-2]
TITLE : 디자인패턴
CONTENT :
내용
FOOTER : 2022.03.03, 유호성
*/


```




## 예문 2
신작 게임의 접속을 구현한다.

접속이라는 알고리즘은 아래와 같이 단계적으로 나눠진다.

- 보안과정
- 인증과정
- 권한과정
- 접속과정



*AbstConnectHelper.java*

```java
package com.TemplatePattern;
// 추상 클래스
public abstract class AbstConnectHelper {
    // 알고리즘의 단계인 경우 외부에 노출이 되면 안된다. 때문에 protected로 선언
    // 외부에서는 호출하면 안되는데, 하위 클래스에서는 사용해야하는 경우
    abstract protected String doSecurity(String info);
    abstract protected String authentication(String id, String password);
    abstract protected int authorization(String userName);
    abstract protected String connection(String info);
    public String requestConnection(String info) {
        String id, password, userName, decodedInfo, userInfo;
        // 암호화된 정보를 복호화 합니다.
        // 암호화된 문자열을 디코드한다.
        decodedInfo = doSecurity(info);
        // 반환된 것을 가지고 아이디, 암호를 할당한다.
        // decodedInfo에서 id 와 password를 추출
        id = "abc";
        password = "abc";
        userInfo = authentication(id, password);
        // userInfo에서 userName을 찾아 냅니다.
        userName = "abc";
        int result = authorization(userName);
        switch (result) {
            case 0:// 무료회원
                break;
            case 1:// 유료회원
                break;
            case 2:// 게임 마스터
                break;
            case 3:// 접속 권한 없음
                break;
            default:
                break;
        }
        return connection(userInfo);
    }
}
```

*ConcreteConnectHelper.java*


```java
package com.TemplatePattern;
public class ConcreteConnectHelper extends AbstConnectHelper {
    @Override
    protected String doSecurity(String info) {
        System.out.println("디코드");
        return info;
    }
    @Override
    protected String authentication(String id, String password) {
        System.out.println("인증확인");
        if(id.equals("abc")|password.equals("abc"))
            return "true info";
        else
            return "false info";
    }
    @Override
    protected int authorization(String userName) {
        System.out.println("권한 확인");
      // 만약 10시 이후로 특정 연령대가 접속 불가한 제한이 있다면,
      // 여기에서 유저의 나이와 같은 정보를 판단해서 
      // 권한 없음이라고 정의한 3을 리턴하면 된다.
      // 현재는 무조건 0을 리턴하는 코드다.
        return 0;
    }
    @Override
    protected String connection(String info) {
        System.out.println("마지막 접속 단계");
        return info;
    }
}
```

*Application.java*

```java
package com.TemplatePattern;
public class Application {
    public static void main(String[] args) {
        AbstConnectHelper abstConnectHelper = new ConcreteConnectHelper();
        abstConnectHelper.requestConnection("아이디 암호 등 접속 정보");
    }
}
/* 출력
디코드
인증확인
권한 확인
마지막 접속 단계
*/
```