
public class Sym implements MathExpression{
    private String val;
    
    public Sym(String value){
        val = value;
    }
    public String getValue(){
        return val;
    }
}