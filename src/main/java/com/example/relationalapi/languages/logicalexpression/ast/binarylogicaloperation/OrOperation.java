package com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;
import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class OrOperation extends BinaryLogicalOperation {
    public OrOperation(Formula leftFormula, Formula rightFormula) {
        super(leftFormula, rightFormula);
    }


    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
