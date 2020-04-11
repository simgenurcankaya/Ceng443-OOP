package hw1;

public class PrintMathMLVisitor implements MathVisitor<String> {
    @Override
    public String visit(Op op) {
        String ret;
        if(op.getOperand().equals("/")){
            //division
            ret = "<mrow><mfrac>";
            ret = ret.concat(op.getFirst().accept(new PrintMathMLVisitor()));
            //ret = ret.concat(" ");
            ret = ret.concat(op.getSecond().accept(new PrintMathMLVisitor()));
            ret = ret.concat("</mfrac></mrow>");
            return  ret;
        }
        else{
            ret = "<mrow><mo>(</mo>";
            ret = ret.concat(op.getFirst().accept(new PrintMathMLVisitor()));
            ret = ret.concat("<mo>");

            if(op.getOperand().equals("*")){
                ret= ret.concat("&times;");
            }
            else if(op.getOperand().equals("+")){
                ret = ret.concat("+");
            }
            else if(op.getOperand().equals("|-")){
                ret = ret.concat("&vdash;");
            }
            ret = ret.concat("</mo>");
            ret = ret.concat(op.getSecond().accept(new PrintMathMLVisitor()));
            ret = ret.concat("<mo>)</mo></mrow>");
            return ret;
        }
    }

    @Override
    public String visit(Num num) {
        String ret = "<mrow><mn>";
        ret = ret.concat(Integer.toString(num.getValue()));
        ret  = ret.concat("</mn></mrow>");
        return ret;
    }

    @Override
    public String visit(Sym sym) {
        String ret = "<mrow><mi>";
        ret = ret.concat(sym.getValue());
        ret  = ret.concat("</mi></mrow>");
        return ret;
    }

    @Override
    public String visit(Var var) {
        String ret = "<mrow><msub><mi>V</mi><mn>";
        ret = ret.concat(Integer.toString(var.getId()));
        ret = ret.concat("</mn></msub>");
        if(var.getPreviousMatch() == null){
            ret = ret.concat("</mrow>");
            return ret;
        }
        else{
            ret = ret.concat("<mo>[</mo>");
            ret = ret.concat(var.getPreviousMatch().accept(new PrintMathMLVisitor()));
            ret = ret.concat("<mo>]</mo></mrow>");
            return  ret;
        }
    }
}
