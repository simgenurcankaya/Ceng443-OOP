public class XDotYDivXIsYRule implements Rule {
    @Override
    public boolean apply(MathExpression me) {
        return false;
    }

    @Override
    public MathExpression getPremise() {
        return null;
    }

    @Override
    public MathExpression getEntails() {
        return null;
    }

    @Override
    public MathExpression entails(MathExpression me) {
        return null;
    }
}
