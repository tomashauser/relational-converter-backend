package app.languages.ra.antlr4visitors;

import app.languages.ra.antlr4files.rastandard.RAStandardBaseVisitor;
import app.languages.ra.antlr4files.rastandard.RAStandardParser;
import app.languages.ra.ast.expression.Expression;
import app.languages.ra.antlr4visitors.expression.StandardExpressionVisitor;
import app.languages.ra.ast.RARoot;

public class StandardRootVisitor extends RAStandardBaseVisitor<RARoot> {
    @Override
    public RARoot visitRoot(RAStandardParser.RootContext ctx) {
        Expression expression = (new StandardExpressionVisitor()).visit(ctx.expr());

        return new RARoot(expression);
    }
}
