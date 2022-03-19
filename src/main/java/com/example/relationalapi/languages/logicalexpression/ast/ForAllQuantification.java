package com.example.relationalapi.languages.logicalexpression.ast;

import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class ForAllQuantification extends Formula implements Quantification {
    public final Formula formula;
    public final String variableName;

    public ForAllQuantification(Formula formula, String variableName) {
        this.formula = formula;
        this.variableName = variableName;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
