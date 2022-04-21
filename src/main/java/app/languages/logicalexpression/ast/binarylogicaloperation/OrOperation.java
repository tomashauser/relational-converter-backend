package app.languages.logicalexpression.ast.binarylogicaloperation;

import app.languages.logicalexpression.ast.Formula;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class OrOperation extends BinaryLogicalOperation {
    public OrOperation(Formula leftFormula, Formula rightFormula) {
        super(leftFormula, rightFormula);
    }


    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
