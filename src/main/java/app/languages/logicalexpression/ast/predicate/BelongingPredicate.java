package app.languages.logicalexpression.ast.predicate;

import app.languages.logicalexpression.ast.Formula;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class BelongingPredicate extends Formula {
    public final String relationName;
    public final String variable;

    public BelongingPredicate(String relationName, String variable) {
        this.relationName = relationName;
        this.variable = variable;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
