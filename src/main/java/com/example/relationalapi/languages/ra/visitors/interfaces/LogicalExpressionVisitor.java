package com.example.relationalapi.languages.ra.visitors.interfaces;

import com.example.relationalapi.languages.logicalexpression.ast.*;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.BelongingPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.NumberTerm;
import com.example.relationalapi.languages.logicalexpression.ast.terms.ColumnSpecification;
import com.example.relationalapi.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import com.example.relationalapi.languages.logicalexpression.ast.terms.StringTerm;

public interface LogicalExpressionVisitor<T> {
    T visit(Formula formula);
    T visit(FormulaParentheses formulaParentheses);

    T visit(AndOperation andOperation);
    T visit(OrOperation orOperation);
    T visit(ComparisonPredicate comparisonPredicate);
    T visit(NumberTerm numberTerm);
    T visit(ColumnSpecification columnSpecification);
    T visit(SpecifiedColumnSpecification specifiedColumnSpecification);
    T visit(StringTerm stringTerm);
    T visit(NotOperation notOperation);

    T visit(ForAllQuantification forAllQuantification);
    T visit(ExistsQuantification existsQuantification);

    T visit(BelongingPredicate belongingPredicate);
}
