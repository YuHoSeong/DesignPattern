package TemplatePattern;

import java.util.ArrayList;

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
