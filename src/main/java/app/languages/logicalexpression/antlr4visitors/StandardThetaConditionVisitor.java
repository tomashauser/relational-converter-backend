package app.languages.logicalexpression.antlr4visitors;

import app.languages.ra.antlr4files.rastandard.RAStandardBaseVisitor;
import app.languages.ra.antlr4files.rastandard.RAStandardParser;
import app.languages.logicalexpression.ast.Formula;

public class StandardThetaConditionVisitor extends RAStandardBaseVisitor<Formula> {
    @Override
    public Formula visitFormulaParentheses(RAStandardParser.FormulaParenthesesContext ctx) {
        return ThetaConditionVisitor.visitFormulaParentheses(ctx, this);
    }

    @Override
    public Formula visitBinaryLogicalOperation(RAStandardParser.BinaryLogicalOperationContext ctx) {
        return ThetaConditionVisitor.visitBinaryLogicalOperation(ctx, this);
    }

    @Override
    public Formula visitNotOperation(RAStandardParser.NotOperationContext ctx) {
        return ThetaConditionVisitor.visitNotOperation(ctx, this);
    }

    @Override
    public Formula visitPredicate(RAStandardParser.PredicateContext ctx) {
        return ThetaConditionVisitor.visitPredicate(ctx, this);
    }

    @Override
    public Formula visitSimpleColumnSpecification(RAStandardParser.SimpleColumnSpecificationContext ctx) {
        return ThetaConditionVisitor.visitSimpleColumnSpecification(ctx);
    }

    @Override
    public Formula visitStringTerm(RAStandardParser.StringTermContext ctx) {
        return ThetaConditionVisitor.visitStringTerm(ctx);
    }

    @Override
    public Formula visitNumberTerm(RAStandardParser.NumberTermContext ctx) {
        return ThetaConditionVisitor.visitNumberTerm(ctx);
    }
}
