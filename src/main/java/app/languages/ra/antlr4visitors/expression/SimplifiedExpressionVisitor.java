package app.languages.ra.antlr4visitors.expression;

import app.languages.logicalexpression.antlr4visitors.SimplifiedThetaConditionVisitor;
import app.languages.logicalexpression.ast.Formula;
import app.languages.ra.antlr4files.rasimplified.RASimplifiedBaseVisitor;
import app.languages.ra.antlr4files.rasimplified.RASimplifiedParser;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class SimplifiedExpressionVisitor extends RASimplifiedBaseVisitor<Expression> {
    private boolean isOuterJoinSymbol(String str, char type) {
        String noLatexOption = "*" + type;
        String simpleLatexOption = "*_" + type;
        String strictLatexOption = "*_{" + type + "}";

        return str.equals(noLatexOption) || str.equals(simpleLatexOption) || str.equals(strictLatexOption);
    }

    @Override
    public Expression visitRelation(RASimplifiedParser.RelationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        return new Relation(name);
    }

    @Override
    public Expression visitJoinOperation(RASimplifiedParser.JoinOperationContext ctx) {
        Expression leftExpression = this.visit(ctx.expr(0));
        RASimplifiedParser.ThetaConditionContext optionalThetaConditionContext = ctx.thetaCondition();

        ThetaCondition thetaCondition;

        if (optionalThetaConditionContext == null) {
            thetaCondition = new ThetaCondition(null);
        } else {
            thetaCondition = (ThetaCondition) this.visit(ctx.thetaCondition());
        }

        Expression rightExpression = this.visit(ctx.expr(1));

        String symbol = ctx.getChild(1).getText();

        switch (symbol) {
            case "*": return new NaturalJoin(leftExpression, rightExpression, thetaCondition);
            case "<*": return new LeftSemijoin(leftExpression, rightExpression, thetaCondition);
            case "*>": return new RightSemijoin(leftExpression, rightExpression, thetaCondition);
            case "\\triangleright": return new LeftAntijoin(leftExpression, rightExpression, thetaCondition);
            case "\\triangleleft": return new RightAntijoin(leftExpression, rightExpression, thetaCondition);
        }

        if (this.isOuterJoinSymbol(symbol, 'L')) {
            return new LeftOuterJoin(leftExpression, rightExpression, thetaCondition);
        }

        if (this.isOuterJoinSymbol(symbol, 'R')) {
            return new RightOuterJoin(leftExpression, rightExpression, thetaCondition);
        }

        if (this.isOuterJoinSymbol(symbol, 'F')) {
            return new FullOuterJoin(leftExpression, rightExpression, thetaCondition);
        }

        String leftSymbol = symbol;
        String rightSymbol = ctx.getChild(3).getText();

        if (leftSymbol.equals("[") && rightSymbol.equals("]")) {
            return new NaturalJoin(leftExpression, rightExpression, thetaCondition);
        }

        if (leftSymbol.equals("\\langle") && rightSymbol.equals("]")) {
            return new LeftSemijoin(leftExpression, rightExpression, thetaCondition);
        }

        if (leftSymbol.equals("[") && rightSymbol.equals("\\rangle")) {
            return new RightSemijoin(leftExpression, rightExpression, thetaCondition);
        }

        return null;
    }

    @Override
    public Expression visitSelection(RASimplifiedParser.SelectionContext ctx) {
        RASimplifiedParser.ThetaConditionContext optionalThetaConditionContext = ctx.thetaCondition();
        ThetaCondition thetaCondition = (ThetaCondition) this.visit(ctx.thetaCondition());
        Expression expression = this.visit(ctx.expr());

        return new Selection(thetaCondition, expression);
    }

    @Override
    public Expression visitProjection(RASimplifiedParser.ProjectionContext ctx) {
        ColumnList columnList = (ColumnList) this.visit(ctx.columnList());
        Expression expression = this.visit(ctx.expr());

        return new Projection(columnList, expression);
    }

    @Override
    public Expression visitRename(RASimplifiedParser.RenameContext ctx) {
        return new Rename((RenameList) this.visit(ctx.renameList()), this.visit(ctx.expr()));
    }

    @Override
    public Expression visitThetaCondition(RASimplifiedParser.ThetaConditionContext ctx) {
        SimplifiedThetaConditionVisitor standardThetaConditionVisitor = new SimplifiedThetaConditionVisitor();

        RASimplifiedParser.FormulaContext formulaContext = ctx.formula();

        Formula formula = formulaContext != null ? standardThetaConditionVisitor.visit(formulaContext) : null;

        return new ThetaCondition(formula);
    }

    @Override
    public Expression visitRenameList(RASimplifiedParser.RenameListContext ctx) {
        List<Pair<String, String>> renameList = new ArrayList<>();

        List<TerminalNode> terminalNodes = ctx.IDENTIFIER();

        for (int i = 0; i < terminalNodes.size(); i += 2) {
            String fst = terminalNodes.get(i).getText();
            String snd = terminalNodes.get(i + 1).getText();

            renameList.add(new Pair<>(fst, snd));
        }

        return new RenameList(renameList);
    }

    // Shared ---------------------------------------------------------------------------------------------------------

    @Override
    public Expression visitBinaryOperation(RASimplifiedParser.BinaryOperationContext ctx) {
        return ExpressionVisitor.visitBinaryOperation(ctx, this);
    }

    @Override
    public Expression visitParentheses(RASimplifiedParser.ParenthesesContext ctx) {
        return ExpressionVisitor.visitParentheses(ctx, this);
    }

    @Override
    public Expression visitColumnList(RASimplifiedParser.ColumnListContext ctx) {
        return ExpressionVisitor.visitColumnList(ctx);
    }
}
