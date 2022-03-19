package com.example.relationalapi.languages.logicalexpression.antlr4visitors;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;
import com.example.relationalapi.languages.logicalexpression.ast.FormulaParentheses;
import com.example.relationalapi.languages.logicalexpression.ast.NotOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public final class ThetaConditionVisitor {
    public static Formula visitFormulaParentheses(ParserRuleContext ctx, AbstractParseTreeVisitor<Formula> visitor) {
        return new FormulaParentheses(visitor.visit(ctx.getChild(1)));
    }
    
    public static Formula visitBinaryLogicalOperation(ParserRuleContext ctx, AbstractParseTreeVisitor<Formula> visitor) {
        String symbol = ctx.getChild(1).getText();
        Formula leftFormula = visitor.visit(ctx.getChild(0));
        Formula rightFormula = visitor.visit(ctx.getChild(2));

        switch (symbol) {
            case "\\land" : return new AndOperation(leftFormula, rightFormula);
            case "\\lor" : return new OrOperation(leftFormula, rightFormula);
            default: return null;
        }
    }

    
    public static Formula visitNotOperation(ParserRuleContext ctx, AbstractParseTreeVisitor<Formula> visitor) {
        Formula formula = visitor.visit(ctx.getChild(1));

        return new NotOperation(formula);
    }

    
    public static Formula visitPredicate(ParserRuleContext ctx, AbstractParseTreeVisitor<Formula> visitor) {
        Term leftTerm = (Term) visitor.visit(ctx.getChild(0));
        Term rightTerm = (Term) visitor.visit(ctx.getChild(2));
        String symbol = ctx.getChild(1).getText();

        return new ComparisonPredicate(leftTerm, rightTerm, symbol);
    }

    
    public static Formula visitSimpleColumnSpecification(ParserRuleContext ctx) {
        return new ColumnSpecification(ctx.getChild(0).getText());
    }

    
    public static Formula visitSpecifiedColumnSpecification(ParserRuleContext ctx) {
        String relation = ctx.getChild(0).getText();
        String column = ctx.getChild(2).getText();

        return new SpecifiedColumnSpecification(relation, column);
    }

    
    public static Formula visitStringTerm(ParserRuleContext ctx) {
        return new StringTerm(ctx.getChild(0).getText());
    }

    
    public static Formula visitNumberTerm(ParserRuleContext ctx) {
        return new NumberTerm(Double.parseDouble(ctx.getChild(0).getText()));
    }
}
