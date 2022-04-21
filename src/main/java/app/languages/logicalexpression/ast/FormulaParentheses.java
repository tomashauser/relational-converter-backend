package app.languages.logicalexpression.ast;

import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class FormulaParentheses extends Formula {
    public final Formula formula;

    public FormulaParentheses(Formula formula) {
        this.formula = formula;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
