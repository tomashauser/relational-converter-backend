package app.languages.logicalexpression.ast;

import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public abstract class Formula {
    public abstract <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor);
}
