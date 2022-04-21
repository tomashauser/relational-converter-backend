package app.languages.logicalexpression.visitors;

import app.languages.logicalexpression.ast.*;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;
import app.languages.logicalexpression.ast.*;
import app.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import app.languages.logicalexpression.ast.binarylogicaloperation.BinaryLogicalOperation;
import app.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import app.languages.logicalexpression.ast.predicate.BelongingPredicate;
import app.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import app.languages.logicalexpression.ast.terms.NumberTerm;
import app.languages.logicalexpression.ast.terms.ColumnSpecification;
import app.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import app.languages.logicalexpression.ast.terms.StringTerm;

public class LogicalExpressionToStringConverter implements LogicalExpressionVisitor<String> {
    private String convertBinaryLogicalOperation(BinaryLogicalOperation binaryLogicalOperation, String symbol) {
        String leftFormula = binaryLogicalOperation.leftFormula.accept(this);
        String rightFormula = binaryLogicalOperation.rightFormula.accept(this);

        return leftFormula + " " + symbol + " " + rightFormula;
    }

    public String visit(Formula formula) {
        if (formula == null) {
            return "";
        }

        return formula.accept(this);
    }

    public String visit(FormulaParentheses formulaParentheses) {
        return "(" + this.visit(formulaParentheses.formula) + ")";
    }

    public String visit(AndOperation andOperation) {
        return this.convertBinaryLogicalOperation(andOperation, "\\land");
    }

    public String visit(OrOperation orOperation) {
        return this.convertBinaryLogicalOperation(orOperation, "\\lor");
    }

    public String visit(ComparisonPredicate comparisonPredicate) {
        String leftTerm = comparisonPredicate.leftTerm.accept(this);
        String rightTerm = comparisonPredicate.rightTerm.accept(this);

        return leftTerm + " " + comparisonPredicate.symbol + " " + rightTerm;
    }

    public String visit(NumberTerm numberTerm) {
        String numberStr = String.valueOf(numberTerm.number);

        if (numberStr.endsWith(".0")) {
            numberStr = numberStr.replace(".0", "");
        }

        return numberStr;
    }

    public String visit(ColumnSpecification columnSpecification) {
        return columnSpecification.column;
    }

    public String visit(SpecifiedColumnSpecification specifiedColumnSpecification) {
        return specifiedColumnSpecification.relation + "." + specifiedColumnSpecification.column;
    }

    public String visit(StringTerm stringTerm) {
        return stringTerm.content;
    }

    public String visit(NotOperation notOperation) {
        return "\\lnot " + notOperation.formula.accept(this);
    }

    private String getQuantificationString(Formula formula, String variableName, String symbol) {
        String inside = this.visit(formula);

        if (!(formula instanceof Quantification)) {
            inside = "(" + inside + ")";
        }

        return symbol + " " + variableName + " \\; " + inside;
    }

    public String visit(ForAllQuantification forAllQuantification) {
        return this.getQuantificationString(forAllQuantification.formula, forAllQuantification.variableName, "\\forall");
    }

    public String visit(ExistsQuantification existsQuantification) {
        return this.getQuantificationString(existsQuantification.formula, existsQuantification.variableName, "\\exists");
    }

    public String visit(BelongingPredicate belongingPredicate) {
        return belongingPredicate.relationName + "(" + belongingPredicate.variable + ")";
    }
}
