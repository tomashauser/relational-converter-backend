package com.example.relationalapi.languages.trc.converters;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;
import com.example.relationalapi.languages.logicalexpression.visitors.LogicalExpressionToStringConverter;

public class TRCToStringConverter {
    private LogicalExpressionToStringConverter logicalExpressionToStringConverter = new LogicalExpressionToStringConverter();

    public String convert(Formula trcFormula) {
        return "\\{ \\; t \\; | \\; " + this.logicalExpressionToStringConverter.visit(trcFormula) + " \\; \\}";
    }
}
