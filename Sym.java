
public class Sym implements MathExpression{
    private String val;
    
    public Sym(String value){
        val = value;
    }
    public String getValue(){
        return val;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if(this.equals(me))
            return true;
        return false;
    }
}