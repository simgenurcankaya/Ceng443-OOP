

public class Op implements MathExpression{

    private String p_operand;
    private MathExpression p_first;
    private MathExpression p_second;

    public Op(String operand,MathExpression first,MathExpression second){
        p_operand = operand;
        p_first = first;
        p_second = second;
    }
    getOperand(){
        return p_operand;
    }
    MathExpression getFirst(){
        return p_first;
    }
    MathExpression getSecond(){
        return p_second;
    }

}