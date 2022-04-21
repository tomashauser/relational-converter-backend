package app.languages.ra.antlr4visitors;

import app.languages.ra.antlr4files.rasimplified.RASimplifiedBaseVisitor;
import app.languages.ra.antlr4files.rasimplified.RASimplifiedParser;
import app.languages.ra.ast.expression.Expression;
import app.languages.ra.antlr4visitors.expression.SimplifiedExpressionVisitor;
import app.languages.ra.ast.RARoot;

public class SimplifiedRootVisitor extends RASimplifiedBaseVisitor<RARoot> {
    @Override
    public RARoot visitRoot(RASimplifiedParser.RootContext ctx) {
        Expression expression = (new SimplifiedExpressionVisitor()).visit(ctx.expr());

        return new RARoot(expression);
    }
}
