package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;
import app.languages.logicalexpression.ast.Formula;

public class ThetaCondition extends Expression {
    public final Formula formula;

    public ThetaCondition(Formula formula) {
        this.formula = formula;
    }

    public boolean isEmpty() {
        return this.formula.toString().isEmpty();
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
