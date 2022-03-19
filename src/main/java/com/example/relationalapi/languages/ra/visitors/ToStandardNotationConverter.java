package com.example.relationalapi.languages.ra.visitors;

import com.example.relationalapi.languages.logicalexpression.visitors.LogicalExpressionToStringConverter;
import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;
import com.example.relationalapi.languages.ra.utils.NotationConverterUtil;
import com.example.relationalapi.languages.ra.visitors.interfaces.LogicalExpressionVisitor;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public class ToStandardNotationConverter implements RAVisitor<String> {
    private LogicalExpressionVisitor<String> thetaConditionToStringConverter = new LogicalExpressionToStringConverter();

    public String visit(Expression expression) {
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

    // TODO: Vyresit problem s σ_{x=y}((π_{name}(R)))
    public String visit(ParenthesesExpression parenthesesExpression) {
        return NotationConverterUtil.convertParentheses(parenthesesExpression, this);
    }

    public String visit(Projection projection) {
        boolean includeParentheses = !(projection.expression instanceof ParenthesesExpression);

        String expr = projection.expression.accept(this);

        if (includeParentheses) {
            expr = "(" + expr + ")";
        }

        return "\\pi_{" + NotationConverterUtil.convertColumnList(projection.columnList) + "}" + expr;
    }

    public String visit(Relation relation) {
        return NotationConverterUtil.convertRelation(relation);
    }

    public String visit(Selection selection) {
        boolean includeParentheses = !(selection.expression instanceof ParenthesesExpression);

        String expr = selection.expression.accept(this);

        if (includeParentheses) {
            expr = "(" + expr + ")";
        }

        return "\\sigma_{"
                + this.thetaConditionToStringConverter.visit(selection.thetaCondition.formula)
                + "}"
                + expr;
    }

    public String visit(FullOuterJoin fullOuterJoin) {
        return this.convertJoinOperation(fullOuterJoin, "\\fullouterjoin");
    }

    public String visit(LeftAntijoin leftAntijoin) {
        return this.convertJoinOperation(leftAntijoin, "\\triangleright");
    }

    public String visit(LeftSemijoin leftSemijoin) {
        return this.convertJoinOperation(leftSemijoin, "\\ltimes");
    }

    public String visit(LeftOuterJoin leftOuterJoin) {
        return this.convertJoinOperation(leftOuterJoin, "\\leftouterjoin");
    }

    public String visit(NaturalJoin naturalJoin) {
        return this.convertJoinOperation(naturalJoin, "\\bowtie");
    }

    public String visit(RightAntijoin rightAntijoin) {
        return this.convertJoinOperation(rightAntijoin, "\\triangleleft");
    }

    public String visit(RightOuterJoin rightOuterJoin) {
        return this.convertJoinOperation(rightOuterJoin, "\\rightouterjoin");
    }

    public String visit(RightSemijoin rightSemijoin) {
        return this.convertJoinOperation(rightSemijoin, "\\rtimes");
    }

    public String visit(Rename rename) {
        List<Pair<String, String>> pairs = rename.renameList.renameList;

        StringBuilder sb;

        if (pairs == null || pairs.isEmpty()) {
            sb = new StringBuilder();
        } else {

            sb = new StringBuilder(pairs.get(0).b + " / " + pairs.get(0).a);

            for (int i = 1; i < pairs.size(); i++) {
                sb.append(", ").append(pairs.get(i).b).append(" / ").append(pairs.get(i).a);
            }
        }

        boolean includeParentheses = !(rename.expression instanceof ParenthesesExpression);

        String expr = rename.expression.accept(this);

        if (includeParentheses) {
            expr = "(" + expr + ")";
        }

        return "\\rho_{" + sb + "}" + expr;
    }

    private String convertJoinOperation(JoinOperation joinOperation, String symbol) {
        String leftExpr = joinOperation.leftExpression.accept(this);
        String rightExpr = joinOperation.rightExpression.accept(this);
        String optionalThetaCondition = this.thetaConditionToStringConverter.visit(joinOperation.thetaCondition.formula);

        if (optionalThetaCondition.isEmpty()) {
            return leftExpr + " " + symbol + " " + rightExpr;
        }

        return leftExpr + " " + symbol + "_{" + optionalThetaCondition + "}" + " " + rightExpr;
    }
}
