package hw1;

public class XDotYDivXIsYRule implements Rule {
    MathExpression premise,entail;
    Var first, second;
    public XDotYDivXIsYRule(Var x, Var y){
        this.first = x;
        this.second = y;
        this.premise =  new Op("/",new Op("*",first,second),first);//new Op("*",first,second);
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
        Op dum = (Op) me;
        Op dum2 = (Op) ((Op) me).getFirst();
        first.setPreviousMatch(dum.getSecond());
        second.setPreviousMatch(dum2.getSecond());
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
