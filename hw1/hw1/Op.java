package hw1;

public class Op implements  MathExpression {

    private String p_operand;
    private MathExpression p_first;
    private MathExpression p_second;

    @Override
    public <T> T accept(MathVisitor<T> visitor) {

        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if(this == me){
            return true;
        }
        if(me instanceof Op){
            Op that = (Op) me;
            return (p_operand == null && that.p_operand ==null)  || (p_operand.equals(that.p_operand) && p_second.match(that.p_second) && p_first.match(that.p_first));
        }
        return false;
    }

    public Op(String operand,MathExpression first,MathExpression second){
        this.p_operand = operand;
        this.p_first = first;
       this.p_second = second;
    }
    String getOperand(){
        return this.p_operand;
    }
    MathExpression getFirst(){
        return this.p_first;
    }
    MathExpression getSecond(){
        return this.p_second;
    }

}
