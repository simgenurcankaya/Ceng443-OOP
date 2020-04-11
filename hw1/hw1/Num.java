package hw1;

public class Num implements MathExpression {

    private int num;

    public Num(int value){
        this.num = value;
    }
    public int getValue(){
        return this.num;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if(this == me){
        return true;
    }
        if(me instanceof Num){
            Num that = (Num) me;
            return (num == that.num);
        }
        return false;
    }
}
