package com.example.relationalapi.languages.logicalexpression.ast.terms;

import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class ColumnSpecification extends Term {
    public final String column;

    public ColumnSpecification(String column) {
        this.column = column;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
