public interface Rule {
    void clear();

    boolean apply(MathExpression me);

    MathExpression getPremise();
    MathExpression getEntails();

    default MathExpression entails(MathExpression me) {
        apply(me);
        return getEntails();
    }
}
