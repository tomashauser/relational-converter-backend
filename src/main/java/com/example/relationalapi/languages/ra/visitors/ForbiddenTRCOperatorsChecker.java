package com.example.relationalapi.languages.ra.visitors;

import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

import java.util.HashSet;
import java.util.Set;

public class ForbiddenTRCOperatorsChecker implements RAVisitor<Void> {
    Set<String> errors;

    public String check(Expression expression) {
        this.errors = new HashSet<>();

        this.visit(expression);

        return this.errors.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }

    public Void visit(Expression expression) {
        expression.accept(this);
        return null;
    }

    public Void visit(CartesianProduct cartesianProduct) {
        this.visit(cartesianProduct.leftExpr);
        this.visit(cartesianProduct.rightExpr);

        return null;
    }

    public Void visit(Difference difference) {
        this.visit(difference.leftExpr);
        this.visit(difference.rightExpr);

        return null;
    }

    public Void visit(Division division) {
        this.visit(division.leftExpr);
        this.visit(division.rightExpr);

        return null;
    }

    public Void visit(Intersection intersection) {
        this.visit(intersection.leftExpr);
        this.visit(intersection.rightExpr);

        return null;
    }

    public Void visit(Union union) {
        this.visit(union.leftExpr);
        this.visit(union.rightExpr);

        return null;
    }

    public Void visit(ThetaCondition thetaCondition) {
        return null;
    }

    public Void visit(ParenthesesExpression parenthesesExpression) {
        this.visit(parenthesesExpression.expression);

        return null;
    }

    public Void visit(Projection projection) {
        this.visit(projection.expression);

        return null;
    }

    public Void visit(Relation relation) {
        return null;
    }

    public Void visit(Selection selection) {
        this.visit(selection.expression);

        return null;
    }

    public Void visit(FullOuterJoin fullOuterJoin) {
        this.visit(fullOuterJoin.leftExpression);
        this.visit(fullOuterJoin.rightExpression);

        this.errors.add("Can't convert full outer join into TRC.");

        return null;
    }

    public Void visit(LeftAntijoin leftAntijoin) {
        this.visit(leftAntijoin.leftExpression);
        this.visit(leftAntijoin.rightExpression);

        return null;
    }

    public Void visit(LeftSemijoin leftSemijoin) {
        this.visit(leftSemijoin.leftExpression);
        this.visit(leftSemijoin.rightExpression);

        return null;
    }

    public Void visit(LeftOuterJoin leftOuterJoin) {
        this.visit(leftOuterJoin.leftExpression);
        this.visit(leftOuterJoin.rightExpression);

        this.errors.add("Can't convert left outer join into TRC.");

        return null;
    }

    public Void visit(NaturalJoin naturalJoin) {
        this.visit(naturalJoin.leftExpression);
        this.visit(naturalJoin.rightExpression);

        return null;
    }

    public Void visit(RightAntijoin rightAntijoin) {
        this.visit(rightAntijoin.leftExpression);
        this.visit(rightAntijoin.rightExpression);

        return null;
    }

    public Void visit(RightOuterJoin rightOuterJoin) {
        this.visit(rightOuterJoin.leftExpression);
        this.visit(rightOuterJoin.rightExpression);

        this.errors.add("Can't convert right outer join into TRC.");

        return null;
    }

    public Void visit(RightSemijoin rightSemijoin) {
        this.visit(rightSemijoin.leftExpression);
        this.visit(rightSemijoin.rightExpression);

        return null;
    }

    public Void visit(Rename rename) {
        this.visit(rename.expression);

        this.errors.add("Can't convert renaming into TRC.");

        return null;
    }
}
