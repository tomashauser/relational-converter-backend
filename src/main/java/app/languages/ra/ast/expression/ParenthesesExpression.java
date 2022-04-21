package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;

public class ParenthesesExpression extends Expression {
    public final Expression expression;

    public ParenthesesExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
