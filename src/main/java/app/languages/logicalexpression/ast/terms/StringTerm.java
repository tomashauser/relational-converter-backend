package app.languages.logicalexpression.ast.terms;

import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class StringTerm extends Term {
    public final String content;

    public StringTerm(String content) {
        this.content = content;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
