package app.languages.logicalexpression.ast.predicate;

import app.languages.logicalexpression.ast.Formula;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;
import app.languages.logicalexpression.ast.terms.Term;

public class ComparisonPredicate extends Formula {
    public final Term leftTerm;
    public final Term rightTerm;
    public final String symbol;

    public ComparisonPredicate(Term leftTerm, Term rightTerm, String symbol) {
        this.leftTerm = leftTerm;
        this.rightTerm = rightTerm;
        this.symbol = symbol;
    }

    @Override
    public <T> T accept(LogicalExpressionVisitor<T> logicalExpressionVisitor) {
        return logicalExpressionVisitor.visit(this);
    }
}
