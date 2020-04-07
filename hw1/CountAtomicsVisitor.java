public class CountAtomicsVisitor implements MathVisitor<Integer> {
    @Override
    public Integer visit(Op op) {
        return op.getFirst().accept(new CountAtomicsVisitor()) + op.getSecond().accept(new CountAtomicsVisitor())  ;
    }

    @Override
    public Integer visit(Num num) {
        return 1;
    }

    @Override
    public Integer visit(Sym sym) {
        return 1;
    }

    @Override
    public Integer visit(Var var) {
        return 1;
    }
}
