package com.example.relationalapi.languages.ra.antlr4visitors;

import com.example.relationalapi.languages.ra.antlr4files.rastandard.RAStandardBaseVisitor;
import com.example.relationalapi.languages.ra.antlr4files.rastandard.RAStandardParser;
import com.example.relationalapi.languages.ra.antlr4visitors.expression.StandardExpressionVisitor;
import com.example.relationalapi.languages.ra.ast.RARoot;
import com.example.relationalapi.languages.ra.ast.expression.Expression;

public class StandardRootVisitor extends RAStandardBaseVisitor<RARoot> {
    @Override
    public RARoot visitRoot(RAStandardParser.RootContext ctx) {
        Expression expression = (new StandardExpressionVisitor()).visit(ctx.expr());

        return new RARoot(expression);
    }
}
