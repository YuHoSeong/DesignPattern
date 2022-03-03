package BridgePattern;

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
