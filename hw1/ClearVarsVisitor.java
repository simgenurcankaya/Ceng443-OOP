public class ClearVarsVisitor implements MathVisitor<Void>{

@Override
public Void visit(final Op op) {
        return  null;
        }

@Override
public Void visit(final Num num) {
        return null;
        }

@Override
public Void visit(final Sym sym) {
        return null;
        }

@Override
public Void visit(final Var var) {
       var.setPreviousMatch(null);
        return null;
}

}