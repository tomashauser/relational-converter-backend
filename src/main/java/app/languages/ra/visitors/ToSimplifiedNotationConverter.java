package app.languages.ra.visitors;

import app.languages.logicalexpression.visitors.LogicalExpressionToStringConverter;
import app.languages.ra.ast.binaryoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import app.languages.ra.utils.NotationConverterUtil;
import app.languages.ra.visitors.interfaces.LogicalExpressionVisitor;
import app.languages.ra.visitors.interfaces.RAVisitor;
import app.languages.ra.ast.binaryoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public final class ToSimplifiedNotationConverter implements RAVisitor<String> {
    private LogicalExpressionVisitor<String> thetaConditionToStringConverter = new LogicalExpressionToStringConverter();
    private boolean formattingEnabled;

    public String visit(Expression expression)  {
        return expression.accept(this);
    }

    public String visit(CartesianProduct cartesianProduct) {
        return NotationConverterUtil.convertBinaryOperation(cartesianProduct, "\\times", this);
    }

    public String visit(Difference difference) {
        return NotationConverterUtil.convertBinaryOperation(difference, "\\setminus", this);
    }

    public String visit(Division division) {
        return NotationConverterUtil.convertBinaryOperation(division, "\\div", this);
    }

    public String visit(Intersection intersection) {
        return NotationConverterUtil.convertBinaryOperation(intersection, "\\cap", this);
    }

    public String visit(Union union) {
        return NotationConverterUtil.convertBinaryOperation(union, "\\cup", this);
    }

    public String visit(ThetaCondition thetaCondition) {
        return this.thetaConditionToStringConverter.visit(thetaCondition.formula);
    }

    public String visit(ParenthesesExpression parenthesesExpression) {
        return NotationConverterUtil.convertParentheses(parenthesesExpression, this);
    }

    public String visit(Projection projection)  {
        String expression = this.visit(projection.expression);

        if (!(projection.expression instanceof Relation)) {
            expression = '(' + expression + ')';
        }

        return expression + "[" + NotationConverterUtil.convertColumnList(projection.columnList) + "]";
    }

    public String visit(Relation relation) {
        return NotationConverterUtil.convertRelation(relation);
    }

    public String visit(Selection selection)  {
        String expression = selection.expression.accept(this);

        if (!(selection.expression instanceof Relation)) {
            expression = '(' + expression + ')';
        }

        return expression + "(" + selection.thetaCondition.accept(this) + ")";
    }

    public String visit(FullOuterJoin fullOuterJoin) {
        return this.convertUndefinedJoin(fullOuterJoin, "*_{F}");
    }

    public String visit(LeftOuterJoin leftOuterJoin) {
        return this.convertUndefinedJoin(leftOuterJoin, "*_{L}");
    }

    public String visit(RightOuterJoin rightOuterJoin) {
        return this.convertUndefinedJoin(rightOuterJoin, "*_{R}");
    }

    public String visit(LeftAntijoin leftAntijoin)  {
        String left = leftAntijoin.leftExpression.accept(this);
        String right = leftAntijoin.rightExpression.accept(this);

        String res = left + " \\triangleright " + right;

        String thetaCondition = leftAntijoin.thetaCondition.accept(this);

        if (thetaCondition.isEmpty()) {
            return res;
        }

        return "(" + left + " \\triangleright " + right + ")(" + thetaCondition + ")";
    }

    public String visit(RightAntijoin rightAntijoin)  {
        String left = rightAntijoin.leftExpression.accept(this);
        String right = rightAntijoin.rightExpression.accept(this);

        String res = left + " \\triangleleft " + right;

        String thetaCondition = rightAntijoin.thetaCondition.accept(this);

        if (thetaCondition.isEmpty()) {
            return res;
        }

        return "(" + left + " \\triangleleft " + right + ")(" + thetaCondition + ")";
    }

    public String visit(LeftSemijoin leftSemijoin) {
        return this.convertJoinOperation(leftSemijoin, "<*", "\\langle", "]");
    }

    public String visit(NaturalJoin naturalJoin) {
        return this.convertJoinOperation(naturalJoin, "*", "[", "]");
    }

    public String visit(RightSemijoin rightSemijoin) {
        return this.convertJoinOperation(rightSemijoin, "*>", "[", "\\rangle");
    }

    @Override
    public String visit(Rename rename) {
        List<Pair<String, String>> pairs = rename.renameList.renameList;

        StringBuilder sb = new StringBuilder(pairs.get(0).a + " \\rightarrow " + pairs.get(0).b);

        for (int i = 1; i < pairs.size(); i++) {
            sb.append(", ").append(pairs.get(i).a).append(" \\rightarrow ").append(pairs.get(i).b);
        }

        String expression = rename.expression.accept(this);

        if (!(rename.expression instanceof Relation)) {
            expression = "(" + expression + ")";
        }

        return expression + " \\langle " + sb + " \\rangle ";
    }

    private String convertJoinOperation(JoinOperation joinOperation, String middleSymbol, String leftSymbol, String rightSymbol) {
        String optionalThetaCondition = joinOperation.thetaCondition.accept(this);

        if (optionalThetaCondition.isEmpty()) {
            return NotationConverterUtil.convertRegularJoinOperation(joinOperation, middleSymbol, this);
        }

        return this.convertThetaJoinOperation(joinOperation, optionalThetaCondition, leftSymbol, rightSymbol);
    }

    private String convertThetaJoinOperation(JoinOperation joinOperation, String optionalThetaCondition, String leftSymbol, String rightSymbol) {
        String leftExpr = joinOperation.leftExpression.accept(this);
        String rightExpr = joinOperation.rightExpression.accept(this);

        return leftExpr + " " + leftSymbol + " " + optionalThetaCondition + " " + rightSymbol + " " + rightExpr;
    }

    private String convertUndefinedJoin(JoinOperation joinOperation, String symbol) {
        String invariant = joinOperation.leftExpression.accept(this) +
                " " + symbol + " "
                + joinOperation.rightExpression.accept(this);
        String thetaCondition = joinOperation.thetaCondition.accept(this);

        if (thetaCondition.isEmpty()) {
            return invariant;
        }

        return "(" + invariant + ")(" + thetaCondition + ")";
    }
}
