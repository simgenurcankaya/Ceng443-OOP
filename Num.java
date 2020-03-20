

public class Num implements MathExpression{
    private int num;
    
    public Num(int value){
        num = value;
    }
    public int getValue(){
        return num;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if(this.equals(me))
            return true;
        return false;
    }
}