package app.languages.ra.ast;

import app.languages.ra.ast.expression.Expression;

public class RARoot {
    public final Expression expression;

    public RARoot(Expression expression) {
        this.expression = expression;
    }
}
