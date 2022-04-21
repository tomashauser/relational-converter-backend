package app.languages.logicalexpression.ast.terms;

import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class SpecifiedColumnSpecification extends Term {
    public final String relation;
    public final String column;

    public SpecifiedColumnSpecification(String relation, String column) {
        this.relation = relation;
        this.column = column;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
