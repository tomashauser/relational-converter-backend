package com.example.relationalapi.languages.trc.converters;

import com.example.relationalapi.languages.logicalexpression.ast.*;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.BelongingPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.ColumnSpecification;
import com.example.relationalapi.languages.logicalexpression.ast.terms.NumberTerm;
import com.example.relationalapi.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import com.example.relationalapi.languages.logicalexpression.ast.terms.StringTerm;
import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;

import java.util.Stack;

public class TRCToPrenexFormConverter implements LogicalExpressionVisitor<Formula> {
    private Stack<Quantification> rememberedQuantifications;

    public Formula convert(Formula formula) {
        this.rememberedQuantifications = new Stack<>();

        Formula result = this.visit(formula);

        int stackSize = this.rememberedQuantifications.size();

        for (int i = 0;  i < stackSize; i++) {
            Quantification q = this.rememberedQuantifications.pop();

            if (q instanceof ForAllQuantification) {
                result = new ForAllQuantification(result, ((ForAllQuantification) q).variableName);
            } else {
                result = new ExistsQuantification(result, ((ExistsQuantification) q).variableName);
            }
        }

        return result;
    }

    public Formula visit(Formula formula) {
        return formula.accept(this);
    }

    public Formula visit(FormulaParentheses formulaParentheses) {
        return new FormulaParentheses(this.visit(formulaParentheses.formula));
    }

    public Formula visit(AndOperation andOperation) {
        Formula left = this.visit(andOperation.leftFormula);
        Formula right = this.visit(andOperation.rightFormula);

        return new AndOperation(left, right);
    }

    public Formula visit(OrOperation orOperation) {
        Formula left = this.visit(orOperation.leftFormula);
        Formula right = this.visit(orOperation.rightFormula);

        return new OrOperation(left, right);
    }

    public Formula visit(ComparisonPredicate comparisonPredicate) {
        return comparisonPredicate;
    }

    public Formula visit(NumberTerm numberTerm) {
        return numberTerm;
    }

    public Formula visit(ColumnSpecification columnSpecification) {
        return columnSpecification;
    }

    public Formula visit(SpecifiedColumnSpecification specifiedColumnSpecification) {
        return specifiedColumnSpecification;
    }

    public Formula visit(StringTerm stringTerm) {
        return stringTerm;
    }

    public Formula visit(NotOperation notOperation) {
        return new NotOperation(this.visit(notOperation.formula));
    }

    public Formula visit(ForAllQuantification forAllQuantification) {
        this.rememberedQuantifications.push(new ForAllQuantification(null, forAllQuantification.variableName));

        return this.visit(forAllQuantification.formula);
    }

    public Formula visit(ExistsQuantification existsQuantification) {
        this.rememberedQuantifications.push(new ExistsQuantification(null, existsQuantification.variableName));

        return this.visit(existsQuantification.formula);
    }

    public Formula visit(BelongingPredicate belongingPredicate) {
        return belongingPredicate;
    }
}
