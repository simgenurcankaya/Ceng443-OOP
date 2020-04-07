public class Var implements MathExpression{
    private int p_id;
    private MathExpression prev_match = null;

    public Var(int id){
        this.p_id = id;
        this.prev_match = null;
    }
    public int getId(){
        return this.p_id;
    }
    public MathExpression getPreviousMatch(){
        return this.prev_match;
    }
    public void setPreviousMatch(MathExpression me){
        this.prev_match = me;
    }
    @Override public boolean match(MathExpression me){
        if(this == me){
            return true;
        }
        if(me instanceof  Var){
            Var that = (Var) me;
            return (prev_match == null) || prev_match.equals(that.prev_match);
        }

/*        if (this.prev_match == null){
            return true;
        }
        else return  this.prev_match.match(me);*/
        return false;
    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

}