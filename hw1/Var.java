package hw1;

public class Var implements MathExpression{
    private int p_id;
    private MathExpression prev_match = null;

    public Var(int id){
        p_id = id;
    }
    public int getId(){
        return p_id;
    }
    public MathExpression getPreviousMatch(){
        return prev_match;
    }
    public void setPreviousMatch(MathExpression me){
        prev_match = me;
    }
    @Override public boolean match(MathExpression me){
        if (this.prev_match == null){
            setPreviousMatch(me);
            return true;
        }
        else{
            if(this.prev_match.equals(me))
                return true;
            return false;
        }
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

}