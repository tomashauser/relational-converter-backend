package com.example.relationalapi.languages.ra.ast.binaryoperation;

import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public class Union extends BinaryOperation {
    public Union(Expression leftExpr, Expression rightExpr) {
        super(leftExpr, rightExpr);
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
