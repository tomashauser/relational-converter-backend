package com.example.relationalapi.languages.logicalexpression.ast.terms;

import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class NumberTerm extends Term {
    public final double number;

    public NumberTerm(double number) {
        this.number = number;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
