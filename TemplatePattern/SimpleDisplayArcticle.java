package TemplatePattern;

import java.util.ArrayList;

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
