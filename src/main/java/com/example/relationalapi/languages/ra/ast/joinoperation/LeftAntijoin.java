package com.example.relationalapi.languages.ra.ast.joinoperation;

import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.ast.expression.ThetaCondition;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public class LeftAntijoin extends JoinOperation {
    public LeftAntijoin(Expression leftExpression, Expression rightExpression, ThetaCondition thetaCondition) {
        super(leftExpression, rightExpression, thetaCondition);
    }


    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
