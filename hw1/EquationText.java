public class EquationText implements DocElement{
    private MathExpression p_math;

    public EquationText(MathExpression innerMath){
        this.p_math = innerMath;
    }
    public MathExpression getInnerMath(){
        return this.p_math;
    }

    @Override
    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }
}