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
        if (this.prev_match == null){
            return true;
        }
        else return  this.prev_match.match(me);

    }

    @Override
    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

}