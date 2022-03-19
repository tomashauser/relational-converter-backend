package com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;

public abstract class BinaryLogicalOperation extends Formula {
    public final Formula leftFormula;
    public final Formula rightFormula;

    protected BinaryLogicalOperation(Formula leftFormula, Formula rightFormula) {
        this.leftFormula = leftFormula;
        this.rightFormula = rightFormula;
    }
}
