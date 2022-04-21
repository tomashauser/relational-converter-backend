package app.languages.logicalexpression.visitors;

import app.languages.logicalexpression.ast.*;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;
import app.languages.logicalexpression.ast.*;
import app.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import app.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import app.languages.logicalexpression.ast.predicate.BelongingPredicate;
import app.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import app.languages.logicalexpression.ast.terms.NumberTerm;
import app.languages.logicalexpression.ast.terms.ColumnSpecification;
import app.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import app.languages.logicalexpression.ast.terms.StringTerm;
import app.utils.Header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SemanticThetaConditionChecker implements LogicalExpressionVisitor<Void> {
    private Header leftHeader;
    private Header rightHeader;

    public List<String> errors;

    public List<String> getErrorsForThetaJoin(Formula formula, Header leftHeader, Header rightHeader) {
        this.errors = new ArrayList<>();
        this.leftHeader = leftHeader;
        this.rightHeader = rightHeader;

        this.visit(formula);

        return this.errors;
    }

    public List<String> getErrorsForSelection(Formula formula, Header header) {
        this.errors = new ArrayList<>();

        if (header == null) {
            return new ArrayList<>(Collections.singleton("Missing a table declaration in selection."));
        }

        this.leftHeader = header;

        if (formula != null) {
            this.visit(formula);
        }

        return this.errors;
    }

    public Void visit(Formula formula) {
        return formula.accept(this);
    }

    public Void visit(FormulaParentheses formulaParentheses) {
        this.visit(formulaParentheses.formula);

        return null;
    }

    public Void visit(AndOperation andOperation) {
        this.visit(andOperation.leftFormula);
        this.visit(andOperation.rightFormula);

        return null;
    }

    public Void visit(OrOperation orOperation) {
        this.visit(orOperation.leftFormula);
        this.visit(orOperation.rightFormula);

        return null;
    }

    public Void visit(ComparisonPredicate comparisonPredicate) {
        this.visit(comparisonPredicate.leftTerm);
        this.visit(comparisonPredicate.rightTerm);

        return null;
    }

    public Void visit(NumberTerm numberTerm) {
        return null;
    }

    public Void visit(ColumnSpecification columnSpecification) {
        String column = columnSpecification.column;

        if (this.rightHeader == null && !leftHeader.getColumns().contains(column)) {
            this.errors.add("Column " + column + " in selection is not in " + leftHeader.getColumnsString() + ".");
        } else if (!this.leftHeader.getColumns().contains(column) && !this.rightHeader.getColumns().contains(column)) {
            this.errors.add("Column " + column + " is neither in " + this.leftHeader.getColumnsString() + " nor in " + this.rightHeader.getColumns() + ".");
        }

        return null;
    }

    public Void visit(SpecifiedColumnSpecification specifiedColumnSpecification) {
        return null;
    }

    public Void visit(StringTerm stringTerm) {
        return null;
    }

    public Void visit(NotOperation notOperation) {
        this.visit(notOperation.formula);

        return null;
    }

    public Void visit(ForAllQuantification forAllQuantification) {
        return null;
    }

    public Void visit(ExistsQuantification existsQuantification) {
        return null;
    }

    public Void visit(BelongingPredicate belongingPredicate) {
        return null;
    }
}
