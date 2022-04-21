package app.languages.trc.converters;

import app.languages.logicalexpression.visitors.LogicalExpressionToStringConverter;
import app.languages.logicalexpression.ast.Formula;

public class TRCToStringConverter {
    private LogicalExpressionToStringConverter logicalExpressionToStringConverter = new LogicalExpressionToStringConverter();

    public String convert(Formula trcFormula) {
        return "\\{ \\; t \\; | \\; " + this.logicalExpressionToStringConverter.visit(trcFormula) + " \\; \\}";
    }
}
