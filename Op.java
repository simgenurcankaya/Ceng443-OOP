

public class Op implements MathExpression{

    private String p_operand;
    private MathExpression p_first;
    private MathExpression p_second;

    public Op(String operand,MathExpression first,MathExpression second){
        p_operand = operand;
        p_first = first;
        p_second = second;
    }
    String getOperand(){
        return p_operand;
    }
    MathExpression getFirst(){
        return p_first;
    }
    MathExpression getSecond(){
        return p_second;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if (this.p_first.equals(me)) 
            return true;
        else if(this.p_second.equals(me))
            return true;
        else 
            return false;

    }
    

}