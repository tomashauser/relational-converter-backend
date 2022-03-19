package com.example.relationalapi.languages.ra.ast.joinoperation;

import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;
import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.ast.expression.ThetaCondition;

public class LeftSemijoin extends JoinOperation {
    public LeftSemijoin(Expression leftExpression, Expression rightExpression, ThetaCondition thetaCondition) {
        super(leftExpression, rightExpression, thetaCondition);
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
