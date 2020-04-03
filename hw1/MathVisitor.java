package hw1;

interface MathVisitor<T>{
    T visit(Op op);
    T visit(Num num);
    T visit(Sym sym);
    T visit(Var var);
}