package com.example.relationalapi.languages.ra.ast.joinoperation;

import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.ast.expression.ThetaCondition;

public abstract class JoinOperation extends Expression {
    public final Expression leftExpression;
    public final Expression rightExpression;
    public final ThetaCondition thetaCondition;

    public JoinOperation(Expression leftExpression, Expression rightExpression, ThetaCondition thetaCondition) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.thetaCondition = thetaCondition;
    }
}
