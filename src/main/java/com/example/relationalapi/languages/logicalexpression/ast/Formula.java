package com.example.relationalapi.languages.logicalexpression.ast;

import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public abstract class Formula {
    public abstract <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor);
}
