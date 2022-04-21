package app.languages.ra.visitors.interfaces;

import app.languages.logicalexpression.ast.*;
import app.languages.logicalexpression.ast.*;
import app.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import app.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import app.languages.logicalexpression.ast.predicate.BelongingPredicate;
import app.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import app.languages.logicalexpression.ast.terms.NumberTerm;
import app.languages.logicalexpression.ast.terms.ColumnSpecification;
import app.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import app.languages.logicalexpression.ast.terms.StringTerm;

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
