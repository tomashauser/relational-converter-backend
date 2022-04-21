package app.languages.ra.ast.binaryoperation;

import app.languages.ra.visitors.interfaces.RAVisitor;
import app.languages.ra.ast.expression.Expression;

public class Union extends BinaryOperation {
    public Union(Expression leftExpr, Expression rightExpr) {
        super(leftExpr, rightExpr);
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
