package com.example.relationalapi.languages.ra.antlr4visitors.expression;

import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.ColumnList;
import com.example.relationalapi.languages.ra.ast.expression.Expression;
import com.example.relationalapi.languages.ra.ast.expression.ParenthesesExpression;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.List;

public final class ExpressionVisitor {
    public static Expression visitBinaryOperation(ParserRuleContext ctx, AbstractParseTreeVisitor<Expression> visitor) {
        Expression leftExpression = visitor.visit(ctx.getChild(0));
        Expression rightExpression = visitor.visit(ctx.getChild(2));

        String symbol = ctx.getChild(1).getText();

        switch(symbol) {
            case "\\setminus": return new Difference(leftExpression, rightExpression);
            case "\\cup": return new Union(leftExpression, rightExpression);
            case "\\cap": return new Intersection(leftExpression, rightExpression);
            case "\\times": return new CartesianProduct(leftExpression, rightExpression);
            case "\\div": return new Division(leftExpression, rightExpression);
            default: return null;
        }
    }

    public static Expression visitParentheses(ParserRuleContext ctx, AbstractParseTreeVisitor<Expression> visitor) {
        Expression expression = visitor.visit(ctx.getChild(1));

        return new ParenthesesExpression(expression);
    }

    public static Expression visitColumnList(ParserRuleContext ctx) {
        List<String> identifiers = new ArrayList<>();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            String current = ctx.getChild(i).getText();

            if (!current.equals(",")) {
                identifiers.add(current);
            }
        }

        return new ColumnList(identifiers);
    }
}
