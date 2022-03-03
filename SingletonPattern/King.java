package SingletonPattern;

public class King {
    // 다른 곳에서 생성자를 호출 할 수 없도록 private으로 지정
    private King() {};

    // 자기 자신만이 이 필드에 접근가능하도록 private 지정
    private static King self = null;

    // synchronized : 멀티쓰레드에서 호출할때 문제 없도록 동기화 하기 위함
    public synchronized static King getInstance(){
        if(self == null){
            self = new King();
        }
        return self;
    }
    
    public void say(){
        System.out.println("I am only one..");
    }
}
