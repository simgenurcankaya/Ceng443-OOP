package hw1;

public class XDotZeroIsZeroRule implements Rule {
    private Var x;
    private MathExpression premise,entail;
    public XDotZeroIsZeroRule(Var var) {
        this.x = var;
        this.premise = new Op("*",x,new Num(0));
        this.entail = new Num(0);
    }

    public Var getX(){
        return  this.x;
    }
    @Override
    public void clear() {
        this.premise = null;
        this.entail = null;
        this.x = null;
    }

    @Override
    public boolean apply(MathExpression me) {
        Op dum = (Op) me;
        x.setPreviousMatch(dum.getFirst());
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
