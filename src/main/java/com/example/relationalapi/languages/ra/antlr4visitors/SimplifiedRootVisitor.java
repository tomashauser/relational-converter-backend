package com.example.relationalapi.languages.ra.antlr4visitors;

import com.example.relationalapi.languages.ra.antlr4files.rasimplified.RASimplifiedBaseVisitor;
import com.example.relationalapi.languages.ra.antlr4files.rasimplified.RASimplifiedParser;
import com.example.relationalapi.languages.ra.antlr4visitors.expression.SimplifiedExpressionVisitor;
import com.example.relationalapi.languages.ra.ast.RARoot;
import com.example.relationalapi.languages.ra.ast.expression.Expression;

public class SimplifiedRootVisitor extends RASimplifiedBaseVisitor<RARoot> {
    @Override
    public RARoot visitRoot(RASimplifiedParser.RootContext ctx) {
        Expression expression = (new SimplifiedExpressionVisitor()).visit(ctx.expr());

        return new RARoot(expression);
    }
}
