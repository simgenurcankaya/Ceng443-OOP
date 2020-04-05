public class Sym implements MathExpression{
    private String val;

    public Sym(String value){
        this.val = value;
    }
    public String getValue(){
        return this.val;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean match(MathExpression me) {
        if(me == this){
            return true;
        }
        if(me instanceof Sym){
            Sym that = (Sym) me;
            return (that.val == null && val ==null )  || (val.equals(that.val));
        }
        return false;
    }
}
