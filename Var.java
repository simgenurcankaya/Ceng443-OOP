
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
        if (prev_match == null){
            setPreviousMatch(me);
            return true;
        }
        else{
            //TODO implement match function
            return false;
        }
    }

}