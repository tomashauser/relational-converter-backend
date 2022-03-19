package com.example.relationalapi.languages.ra.ast.binaryoperation;

import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public class Difference extends BinaryOperation {
    public Difference(Expression leftExpr, Expression rightExpr) {
        super(leftExpr, rightExpr);
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
