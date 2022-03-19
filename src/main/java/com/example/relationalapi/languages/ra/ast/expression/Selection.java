package com.example.relationalapi.languages.ra.ast.expression;

import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public class Selection extends Expression {
    public final ThetaCondition thetaCondition;
    public final Expression expression;

    public Selection(ThetaCondition thetaCondition, Expression expression) {
        this.thetaCondition = thetaCondition;
        this.expression = expression;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
