
public class EquationText implements DocElement{
    private MathExpression p_math;

    public EquationText(MathExpression innerMath){
        p_math = innerMath;
    }
    public MathExpression getInnerMath(){
        return p_math;
    }
}