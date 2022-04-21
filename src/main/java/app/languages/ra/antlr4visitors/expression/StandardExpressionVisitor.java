package app.languages.ra.antlr4visitors.expression;

import app.languages.logicalexpression.antlr4visitors.StandardThetaConditionVisitor;
import app.languages.logicalexpression.ast.Formula;
import app.languages.ra.antlr4files.rastandard.RAStandardBaseVisitor;
import app.languages.ra.antlr4files.rastandard.RAStandardParser;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class StandardExpressionVisitor extends RAStandardBaseVisitor<Expression> {
    @Override
    public Expression visitSelection(RAStandardParser.SelectionContext ctx) {
        ThetaCondition thetaCondition = (ThetaCondition) this.visit(ctx.thetaCondition());
        Expression expression = this.visit(ctx.expr());

        return new Selection(thetaCondition, expression);
    }

    @Override
    public Expression visitJoinOperation(RAStandardParser.JoinOperationContext ctx) {
        Expression leftExpression = this.visit(ctx.expr(0));

        ThetaCondition thetaCondition;

        try {
            thetaCondition = (ThetaCondition) this.visit(ctx.thetaCondition());
        } catch (Exception e) {
            thetaCondition = new ThetaCondition(null);
        }

        Expression rightExpression = this.visit(ctx.expr(1));

        String symbol = ctx.getChild(1).getText();

        switch (symbol) {
            case "\\bowtie": return new NaturalJoin(leftExpression, rightExpression, thetaCondition);
            case "\\ltimes": return new LeftSemijoin(leftExpression, rightExpression, thetaCondition);
            case "\\rtimes": return new RightSemijoin(leftExpression, rightExpression, thetaCondition);
            case "\\triangleright": return new LeftAntijoin(leftExpression, rightExpression, thetaCondition);
            case "\\triangleleft": return new RightAntijoin(leftExpression, rightExpression, thetaCondition);
            case "\\leftouterjoin": return new LeftOuterJoin(leftExpression, rightExpression, thetaCondition);
            case "\\rightouterjoin": return new RightOuterJoin(leftExpression, rightExpression, thetaCondition);
            case "\\fullouterjoin": return new FullOuterJoin(leftExpression, rightExpression, thetaCondition);
            default: return null;
        }
    }

    @Override
    public Expression visitProjection(RAStandardParser.ProjectionContext ctx) {
        ColumnList columnList = (ColumnList) this.visit(ctx.columnList());
        Expression expression = this.visit(ctx.expr());

        return new Projection(columnList, expression);
    }

    @Override
    public Expression visitRelation(RAStandardParser.RelationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        return new Relation(name);
    }

    @Override
    public Expression visitThetaCondition(RAStandardParser.ThetaConditionContext ctx) {
        StandardThetaConditionVisitor standardThetaConditionVisitor = new StandardThetaConditionVisitor();

        RAStandardParser.FormulaContext formulaContext = ctx.formula();

        Formula formula = formulaContext != null ? standardThetaConditionVisitor.visit(formulaContext) : null;

        return new ThetaCondition(formula);
    }

    @Override
    public Expression visitRename(RAStandardParser.RenameContext ctx) {
        return new Rename((RenameList) this.visit(ctx.renameList()), this.visit(ctx.expr()));
    }

    @Override
    public Expression visitRenameList(RAStandardParser.RenameListContext ctx) {
        List<Pair<String, String>> renameList = new ArrayList<>();

        List<TerminalNode> terminalNodes = ctx.IDENTIFIER();

        for (int i = 0; i < terminalNodes.size(); i += 2) {
            String fst = terminalNodes.get(i).getText();
            String snd = terminalNodes.get(i + 1).getText();

            renameList.add(new Pair<>(snd, fst));
        }

        return new RenameList(renameList);
    }

    // Shared ---------------------------------------------------------------------------------------------------------

    @Override
    public Expression visitParentheses(RAStandardParser.ParenthesesContext ctx) {
        Expression expression = this.visit(ctx.expr());

        return new ParenthesesExpression(expression);
    }

    @Override
    public Expression visitBinaryOperation(RAStandardParser.BinaryOperationContext ctx) {
        return ExpressionVisitor.visitBinaryOperation(ctx, this);
    }

    @Override
    public Expression visitColumnList(RAStandardParser.ColumnListContext ctx) {
        return ExpressionVisitor.visitColumnList(ctx);
    }
}
