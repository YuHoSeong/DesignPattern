package BridgePattern;

public class MainEntry {
    public static void main(String[] args) {
        String title = "디자인 패턴";
        String author = "유호성";
        String[] content = {"브릿지 패턴","bridge pattern"};

        Draft draft = new Draft(title, author, content);

        Display display1 = new SimpleDisplay();
        // draft.print(display1);

        Display display2 = new CaptionDisplay();
        // draft.print(display2);

        String publisher = "출판사";
        int cost = 100;

        Publication publication = new Publication(title, author, content, publisher, cost);

        publication.print(display2);
    }
    
}
