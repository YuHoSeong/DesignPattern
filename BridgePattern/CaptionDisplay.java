package BridgePattern;

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
