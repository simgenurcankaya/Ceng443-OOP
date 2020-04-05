public interface Rule {
    default void clear() {

    }

    boolean apply(MathExpression me);
    MathExpression getPremise();
    MathExpression getEntails();
    MathExpression entails(MathExpression me);
}
