public class XDotYDivXIsYRule implements Rule{

    @Override
    public boolean apply(MathExpression me) {
        clear();
        if(me.match(this))
            
        return false;
    }

    @Override
    public MathExpression getPremise() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MathExpression getEntails() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MathExpression entails(MathExpression me) {
        // TODO Auto-generated method stub
        return null;
    }
    
}