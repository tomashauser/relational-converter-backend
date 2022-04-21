package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;

public class Rename extends Expression {
    public final RenameList renameList;
    public final Expression expression;

    public Rename(RenameList renameList, Expression expression) {
        this.renameList = renameList;
        this.expression = expression;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
