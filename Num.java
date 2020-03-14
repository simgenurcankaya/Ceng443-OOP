

public class Num implements MathExpression{
    private int num;
    
    public Num(int value){
        num = value;
    }
    public int getValue(){
        return num;
    }
}