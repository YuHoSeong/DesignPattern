package SingletonPattern;

public class MainEntry {
    public static void main(String[] args) {
        // King king = new King();
        // 객체 생성 불가능
        
        King king = King.getInstance();
        king.say(); // 출력 : I am only one..
        King king2 = King.getInstance();

        if(king == king2){
            System.out.println("same object");
        }else{
            System.out.println("different object");
        }
    }
    // 출력 : same object
    
}
