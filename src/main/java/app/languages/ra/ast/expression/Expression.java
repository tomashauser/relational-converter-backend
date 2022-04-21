package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;

public abstract class Expression {
   public abstract <T> T accept(RAVisitor<T> raVisitor);
}
