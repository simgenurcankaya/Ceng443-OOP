package hw1;

public class XPlusXIs2XRule implements Rule {
    private Var x;
    private  MathExpression premise,entail;

    public XPlusXIs2XRule(Var var) {
        this.x = var;
        this.premise = new Op("+",var,var);
        this.entail = new Op("*",new Num(2),var);

    }


    @Override
    public boolean apply(MathExpression me) {
        //x.setPreviousMatch(me);
        this.clear();
        Op dum = (Op) me;
        if(dum.getFirst().match(dum.getSecond())) {
            x.setPreviousMatch(dum.getFirst());
            return true;
        }
        else return false;
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
