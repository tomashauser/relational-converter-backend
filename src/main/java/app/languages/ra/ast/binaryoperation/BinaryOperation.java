package app.languages.ra.ast.binaryoperation;

import app.languages.ra.ast.expression.Expression;

public abstract class BinaryOperation extends Expression {
    public final Expression leftExpr;
    public final Expression rightExpr;

    public BinaryOperation(Expression leftExpr, Expression rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }
}
