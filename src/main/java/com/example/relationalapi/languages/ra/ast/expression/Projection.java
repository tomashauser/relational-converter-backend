package com.example.relationalapi.languages.ra.ast.expression;

import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public class Projection extends Expression {
    public final ColumnList columnList;
    public final Expression expression;

    public Projection(ColumnList columnList, Expression expression) {
        this.columnList = columnList;
        this.expression = expression;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
