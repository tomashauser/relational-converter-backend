package app.languages.ra.utils;

import app.languages.ra.ast.binaryoperation.BinaryOperation;
import app.languages.ra.ast.expression.ColumnList;
import app.languages.ra.ast.expression.ParenthesesExpression;
import app.languages.ra.ast.expression.Relation;
import app.languages.ra.ast.joinoperation.JoinOperation;
import app.languages.ra.visitors.interfaces.RAVisitor;

import java.util.ArrayList;
import java.util.List;

public final class NotationConverterUtil {
    public static String convertBinaryOperation(BinaryOperation binaryOperation, String symbol, RAVisitor<String> raVisitor) {
        String leftExpr = binaryOperation.leftExpr.accept(raVisitor);
        String rightExpr = binaryOperation.rightExpr.accept(raVisitor);

        return leftExpr + " " + symbol + " " + rightExpr;
    }

    public static String convertRegularJoinOperation(JoinOperation joinOperation, String symbol, RAVisitor<String> raVisitor) {
        String leftExpr = joinOperation.leftExpression.accept(raVisitor);
        String rightExpr = joinOperation.rightExpression.accept(raVisitor);

        return leftExpr + " " + symbol + " " + rightExpr;
    }

    public static String convertColumnList(ColumnList columnList) {
        List<String> projectionColumns = new ArrayList<>(columnList.columnNames);

        StringBuilder ret = new StringBuilder(projectionColumns.get(0));

        for (int i = 1; i < projectionColumns.size(); i++) {
            ret.append(",").append(projectionColumns.get(i));
        }

        return ret.toString();
    }

    public static String convertParentheses(ParenthesesExpression parenthesesExpression, RAVisitor<String> raVisitor) {
        return "(" + parenthesesExpression.expression.accept(raVisitor) + ")";
    }

    public static String convertRelation(Relation relation) {
        return relation.name;
    }
}
