package com.example.relationalapi.languages.trc.converters;

import com.example.relationalapi.languages.logicalexpression.ast.*;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.BelongingPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.*;
import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

public class ToTRCPredicateConverter implements LogicalExpressionVisitor<Formula> {
    // Tuple variable to assign
    private String currentTupleVariable;

    private final String MAIN_TUPLE_VARIABLE;

    public ToTRCPredicateConverter(String MAIN_TUPLE_VARIABLE) {
        this.MAIN_TUPLE_VARIABLE = MAIN_TUPLE_VARIABLE;
    }

    public Formula convert(Formula formula, String tupleVariable) {
        this.currentTupleVariable = tupleVariable;

        return this.visit(formula);
    }

    public Formula visit(Formula formula) {
        return formula.accept(this);
    }

    public Formula visit(FormulaParentheses formulaParentheses) {
        return new FormulaParentheses(this.visit(formulaParentheses.formula));
    }

    public Formula visit(AndOperation andOperation) {
        return new AndOperation(this.visit(andOperation.leftFormula), this.visit(andOperation.rightFormula));
    }

    public Formula visit(OrOperation orOperation) {
        return new OrOperation(this.visit(orOperation.leftFormula), this.visit(orOperation.rightFormula));
    }

    public Formula visit(ComparisonPredicate comparisonPredicate) {
        return new ComparisonPredicate((Term) this.visit(comparisonPredicate.leftTerm), (Term) this.visit(comparisonPredicate.rightTerm), comparisonPredicate.symbol);
    }

    public Formula visit(NumberTerm numberTerm) {
        return numberTerm;
    }

    public Formula visit(ColumnSpecification columnSpecification) {
        return new SpecifiedColumnSpecification(this.MAIN_TUPLE_VARIABLE, columnSpecification.column);
    }

    // Rewrites t.a = q.a to p.a = q.a but not r.a = q.a
    public Formula visit(SpecifiedColumnSpecification specifiedColumnSpecification) {
        if (specifiedColumnSpecification.relation.equals(this.MAIN_TUPLE_VARIABLE)) {
            return new SpecifiedColumnSpecification(this.currentTupleVariable, specifiedColumnSpecification.column);
        }

        return new SpecifiedColumnSpecification(specifiedColumnSpecification.relation, specifiedColumnSpecification.column);
    }

    public Formula visit(StringTerm stringTerm) {
        return stringTerm;
    }

    public Formula visit(NotOperation notOperation) {
        return new NotOperation(this.visit(notOperation.formula));
    }

    // Doesnt' rewrite anything as there cannot be a "t" in quantifier
    public Formula visit(ForAllQuantification forAllQuantification) {
        return new ForAllQuantification(this.visit(forAllQuantification.formula), forAllQuantification.variableName);
    }

    // Doesnt' rewrite anything as there cannot be a "t" in quantifier
    public Formula visit(ExistsQuantification existsQuantification) {
        return new ExistsQuantification(this.visit(existsQuantification.formula), existsQuantification.variableName);
    }

    // Rewrites R(t) to R(p) but not R(s) to R(p)
    public Formula visit(BelongingPredicate belongingPredicate) {
        if (belongingPredicate.variable.equals(this.MAIN_TUPLE_VARIABLE)) {
            return new BelongingPredicate(belongingPredicate.relationName, this.currentTupleVariable);
        }

        return new BelongingPredicate(belongingPredicate.relationName, belongingPredicate.variable);
    }
}
