package app.languages.logicalexpression.ast;

import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class NotOperation extends Formula {
    public final Formula formula;

    public NotOperation(Formula formula) {
        this.formula = formula;
    }

    @Override
    public String toString() {
        return "¬" + this.formula.toString();
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
