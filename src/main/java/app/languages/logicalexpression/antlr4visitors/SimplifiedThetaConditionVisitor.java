package app.languages.logicalexpression.antlr4visitors;

import app.languages.ra.antlr4files.rasimplified.RASimplifiedBaseVisitor;
import app.languages.ra.antlr4files.rasimplified.RASimplifiedParser;
import app.languages.logicalexpression.ast.Formula;

public class SimplifiedThetaConditionVisitor extends RASimplifiedBaseVisitor<Formula> {
    @Override
    public Formula visitFormulaParentheses(RASimplifiedParser.FormulaParenthesesContext ctx) {
        return ThetaConditionVisitor.visitFormulaParentheses(ctx, this);
    }

    @Override
    public Formula visitBinaryLogicalOperation(RASimplifiedParser.BinaryLogicalOperationContext ctx) {
        return ThetaConditionVisitor.visitBinaryLogicalOperation(ctx, this);
    }

    @Override
    public Formula visitNotOperation(RASimplifiedParser.NotOperationContext ctx) {
        return ThetaConditionVisitor.visitNotOperation(ctx, this);
    }

    @Override
    public Formula visitPredicate(RASimplifiedParser.PredicateContext ctx) {
        return ThetaConditionVisitor.visitPredicate(ctx, this);
    }

    @Override
    public Formula visitSimpleColumnSpecification(RASimplifiedParser.SimpleColumnSpecificationContext ctx) {
        return ThetaConditionVisitor.visitSimpleColumnSpecification(ctx);
    }

    @Override
    public Formula visitStringTerm(RASimplifiedParser.StringTermContext ctx) {
        return ThetaConditionVisitor.visitStringTerm(ctx);
    }

    @Override
    public Formula visitNumberTerm(RASimplifiedParser.NumberTermContext ctx) {
        return ThetaConditionVisitor.visitNumberTerm(ctx);
    }
}
