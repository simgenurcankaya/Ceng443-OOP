public class XPlusXIs2XRule implements Rule {
    private Var x;
    private  MathExpression premise,entail;

    public XPlusXIs2XRule(Var var) {
        this.x = var;
        this.premise = new Op("+",var,var);
        this.entail = new Op("*",new Num(2),var);

    }

    @Override
    public void clear() {
        this.premise = null;
        this.entail = null;
        this.x = null;
    }

    @Override
    public boolean apply(MathExpression me) {
        x.setPreviousMatch(me);
        return true;
    }

    @Override
    public MathExpression getPremise() {
        return this.premise;
    }

    @Override
    public MathExpression getEntails() {
        return this.entail;
    }
}
