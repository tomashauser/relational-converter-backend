package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;

public class Relation extends Expression {
    public final String name;

    public Relation(String name) {
        this.name = name;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
