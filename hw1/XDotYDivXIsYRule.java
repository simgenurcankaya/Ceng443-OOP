public class XDotYDivXIsYRule implements Rule {
    MathExpression premise,entail;
    Var first, second;
    public XDotYDivXIsYRule(Var x, Var y){
        this.first = x;
        this.second = y;
        this.premise = new Op("*",first,second);
        this.entail = second;
    }
    public Var getX(){
        return this.first;
    }

    public Var getY(){
        return this.second;
    }

    @Override
    public void clear() {
        this.first = null;
        this.second = null;
    }

    @Override
    public boolean apply(MathExpression me) {
        return false;
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
