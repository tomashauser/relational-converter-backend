package com.example.relationalapi.languages.ra.visitors;

import com.example.relationalapi.languages.logicalexpression.ast.*;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.BelongingPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.SpecifiedColumnSpecification;
import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;
import com.example.relationalapi.languages.ra.utils.FreshVariableGenerator;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;
import com.example.relationalapi.languages.trc.converters.ToTRCPredicateConverter;
import com.example.relationalapi.utils.Header;
import com.example.relationalapi.utils.HeaderEvaluator;
import com.example.relationalapi.utils.Schema;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class RAToTRCConverter implements RAVisitor<Pair<Header, Formula>> {
    private final Schema schema;
    private final String TUPLE_VARIABLE_NAME = "t";
    private final FreshVariableGenerator freshVariableGenerator
            = new FreshVariableGenerator(Arrays.asList("p", "q", "r", "s"), i -> "x_{" + i + "}");
    private ToTRCPredicateConverter toTRCPredicateConverter = new ToTRCPredicateConverter(this.TUPLE_VARIABLE_NAME);


    public RAToTRCConverter(Schema schema) {
        this.schema = schema;
    }

    private Formula getAndFromColumns(List<String> columns, String var1, String var2) {
        if (columns == null || columns.size() == 0) {
            return null;
        }

        int size = columns.size();

        if (size == 1) {
            SpecifiedColumnSpecification left = new SpecifiedColumnSpecification(var1, columns.get(0));
            SpecifiedColumnSpecification right = new SpecifiedColumnSpecification(var2, columns.get(0));

            return new ComparisonPredicate(left, right, "=");
        }

        List<String> first = new ArrayList<>(columns.subList(0, (size + 1)/2));
        List<String> second = new ArrayList<>(columns.subList((size + 1)/2, size));

        return new AndOperation(this.getAndFromColumns(first, var1, var2), this.getAndFromColumns(second, var1, var2));
    }

    public Pair<Header, Formula> visit(Expression expression) {
        return expression.accept(this);
    }

    public Pair<Header, Formula> visit(CartesianProduct cartesianProduct) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(cartesianProduct.leftExpr);
        Pair<Header, Formula> rightSubtreeResult = this.visit(cartesianProduct.rightExpr);

        String freshVariable1 = freshVariableGenerator.getFreshVariable();
        String freshVariable2 = freshVariableGenerator.getFreshVariable();

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation andBetweenSubtreeResults = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        Formula andForLeftColumns = this.getAndFromColumns(leftSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable1);
        Formula andForRightColumns = this.getAndFromColumns(rightSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable2);

        AndOperation andBetweenColumns = new AndOperation(andForLeftColumns, andForRightColumns);

        AndOperation finalAnd = new AndOperation(andBetweenSubtreeResults, andBetweenColumns);

        ExistsQuantification existsQuantification1 = new ExistsQuantification(finalAnd, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateCartesianProduct(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visit(Division division) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(division.leftExpr);
        Pair<Header, Formula> rightSubtreeResult = this.visit(division.rightExpr);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Set<String> difference = leftSubtreeResult.a.getColumnsCopy();
        difference.removeAll(rightSubtreeResult.a.getColumns());

        Formula andBetweenRight = this.getAndFromColumns(rightSubtreeResult.a.getListData(), freshVariable2, freshVariable1);
        Formula andBetweenDifference = this.getAndFromColumns(new ArrayList<>(difference), this.TUPLE_VARIABLE_NAME, freshVariable2);
        AndOperation andBetweenRightAndDifference = new AndOperation(andBetweenRight, andBetweenDifference);

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        ExistsQuantification existsQuantification = new ExistsQuantification(new AndOperation(rewrittenRightSubtreeResult, andBetweenRightAndDifference), freshVariable2);

        NotOperation not;

        if (rewrittenLeftSubtreeResult instanceof BelongingPredicate) {
            not = new NotOperation(rewrittenLeftSubtreeResult);
        } else {
            not = new NotOperation(new FormulaParentheses(rewrittenLeftSubtreeResult));
        }

        OrOperation or = new OrOperation(not, existsQuantification);

        ForAllQuantification forAllQuantification = new ForAllQuantification(or, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateDivision(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, forAllQuantification);
    }

    public Pair<Header, Formula> visit(Union union) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(union.leftExpr);
        Pair<Header, Formula> rightSubtreeResult = this.visit(union.rightExpr);

        OrOperation orOperation;

        Formula leftFormula = leftSubtreeResult.b;
        Formula rightFormula = rightSubtreeResult.b;

        if (!(leftFormula instanceof BelongingPredicate)) {
            leftFormula = new FormulaParentheses(leftFormula);
        }

        if (!(rightFormula instanceof  BelongingPredicate)) {
            rightFormula = new FormulaParentheses(rightFormula);
        }

        orOperation = new OrOperation(leftFormula, rightFormula);

        return new Pair<>(leftSubtreeResult.a, orOperation);
    }

    public Pair<Header, Formula> visit(Intersection intersection) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(intersection.leftExpr);
        Pair<Header, Formula> rightSubtreeResult = this.visit(intersection.rightExpr);

        AndOperation andOperation = new AndOperation(leftSubtreeResult.b, rightSubtreeResult.b);

        return new Pair<>(leftSubtreeResult.a, andOperation);
    }

    public Pair<Header, Formula> visit(Difference difference) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(difference.leftExpr);
        Pair<Header, Formula> rightSubtreeResult = this.visit(difference.rightExpr);

        AndOperation andOperation = new AndOperation(leftSubtreeResult.b, new NotOperation(rightSubtreeResult.b));

        return new Pair<>(leftSubtreeResult.a, andOperation);
    }

    public Pair<Header, Formula> visit(ParenthesesExpression parenthesesExpression) {
        Pair<Header, Formula> subtreeResult = this.visit(parenthesesExpression.expression);

        return new Pair<>(subtreeResult.a, new FormulaParentheses(subtreeResult.b));
    }

    public Pair<Header, Formula> visit(Projection projection) {
        Pair<Header, Formula> subtreeResult = this.visit(projection.expression);

        Set<String> columnsSet = new HashSet<>(projection.columnList.columnNames);

        if (columnsSet.equals(subtreeResult.a.getColumns())) {
            Header newHeader = HeaderEvaluator.evaluateProjection(projection, subtreeResult.a);

            return new Pair<>(newHeader, subtreeResult.b);
        }

        String freshVariable = this.freshVariableGenerator.getFreshVariable();

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(subtreeResult.b, freshVariable);

        Formula ands = this.getAndFromColumns(projection.columnList.columnNames, "t", freshVariable);

        AndOperation andOperation = new AndOperation(rewrittenLeftSubtreeResult, ands);

        ExistsQuantification result = new ExistsQuantification(andOperation, freshVariable);

        Header newHeader = HeaderEvaluator.evaluateProjection(projection, subtreeResult.a);

        return new Pair<>(newHeader, result);
    }

    public Pair<Header, Formula> visit(Relation relation) {
        Header header = this.schema.getTableHeaderByName(relation.name);
        BelongingPredicate belongingPredicate = new BelongingPredicate(relation.name, this.TUPLE_VARIABLE_NAME);

        return new Pair<>(header, belongingPredicate);
    }

    public Pair<Header, Formula> visit(Selection selection) {
        Pair<Header, Formula> subtreeResult = this.visit(selection.expression);

        if (selection.thetaCondition.formula == null) {
            return new Pair<>(subtreeResult.a, subtreeResult.b);
        }

        Formula rewrittenPredicate = this.toTRCPredicateConverter.convert(selection.thetaCondition.formula, this.TUPLE_VARIABLE_NAME);
        Formula andOperation = new AndOperation(subtreeResult.b, rewrittenPredicate);

        return new Pair<>(subtreeResult.a, andOperation);
    }

    public Pair<Header, Formula> visit(LeftAntijoin leftAntijoin) {
        RightSemijoin rightSemijoin = new RightSemijoin(leftAntijoin.leftExpression, leftAntijoin.rightExpression, leftAntijoin.thetaCondition);
        Difference difference = new Difference(leftAntijoin.rightExpression, rightSemijoin);

        return this.visit(difference);
    }

    public Pair<Header, Formula> visit(RightAntijoin rightAntijoin) {
        LeftSemijoin leftSemijoin = new LeftSemijoin(rightAntijoin.leftExpression, rightAntijoin.rightExpression, rightAntijoin.thetaCondition);
        Difference difference = new Difference(rightAntijoin.leftExpression, leftSemijoin);

        return this.visit(difference);
    }

    public Pair<Header, Formula> visitLeftThetaSemijoin(LeftSemijoin leftSemijoin) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(leftSemijoin.leftExpression);
        Pair<Header, Formula> rightSubtreeResult = this.visit(leftSemijoin.rightExpression);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation and = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        and = new AndOperation(and, this.getAndFromColumns(leftSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable1));

        and = new AndOperation(and, this.toTRCPredicateConverter.visit(leftSemijoin.thetaCondition.formula));

        ExistsQuantification existsQuantification1 = new ExistsQuantification(and, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateRightSemijoin(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visit(LeftSemijoin leftSemijoin) {
        if (leftSemijoin.thetaCondition.formula != null) {
            return this.visitLeftThetaSemijoin(leftSemijoin);
        }

        Pair<Header, Formula> leftSubtreeResult = this.visit(leftSemijoin.leftExpression);
        Pair<Header, Formula> rightSubtreeResult = this.visit(leftSemijoin.rightExpression);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Set<String> leftSubtreeColumnsCopy = leftSubtreeResult.a.getColumnsCopy();
        Set<String> rightSubtreeColumnsCopy = rightSubtreeResult.a.getColumnsCopy();

        Set<String> commonColumns = new HashSet<>(leftSubtreeColumnsCopy);
        commonColumns.retainAll(rightSubtreeColumnsCopy);

        Formula andBetweenCommon = this.getAndFromColumns(new ArrayList<>(commonColumns), freshVariable1, freshVariable2);
        Formula andBetweenLeft = this.getAndFromColumns(leftSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable1);

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation and = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        if (andBetweenCommon != null) {
            and = new AndOperation(and, andBetweenCommon);
        }

        and = new AndOperation(and, andBetweenLeft);

        ExistsQuantification existsQuantification1 = new ExistsQuantification(and, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateLeftSemijoin(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visitRightThetaSemijoin(RightSemijoin rightSemijoin) {
        Pair<Header, Formula> leftSubtreeResult = this.visit(rightSemijoin.leftExpression);
        Pair<Header, Formula> rightSubtreeResult = this.visit(rightSemijoin.rightExpression);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation and = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        and = new AndOperation(and, this.getAndFromColumns(rightSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable2));

        and = new AndOperation(and, this.toTRCPredicateConverter.visit(rightSemijoin.thetaCondition.formula));

        ExistsQuantification existsQuantification1 = new ExistsQuantification(and, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateRightSemijoin(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visit(RightSemijoin rightSemijoin) {
        if (rightSemijoin.thetaCondition.formula != null) {
            return this.visitRightThetaSemijoin(rightSemijoin);
        }

        Pair<Header, Formula> leftSubtreeResult = this.visit(rightSemijoin.leftExpression);
        Pair<Header, Formula> rightSubtreeResult = this.visit(rightSemijoin.rightExpression);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Set<String> leftSubtreeColumnsCopy = leftSubtreeResult.a.getColumnsCopy();
        Set<String> rightSubtreeColumnsCopy = rightSubtreeResult.a.getColumnsCopy();

        Set<String> commonColumns = new HashSet<>(leftSubtreeColumnsCopy);
        commonColumns.retainAll(rightSubtreeColumnsCopy);

        Formula andBetweenCommon = this.getAndFromColumns(new ArrayList<>(commonColumns), freshVariable1, freshVariable2);
        Formula andBetweenRight = this.getAndFromColumns(rightSubtreeResult.a.getListData(), this.TUPLE_VARIABLE_NAME, freshVariable1);

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation and = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        if (andBetweenCommon != null) {
            and = new AndOperation(and, andBetweenCommon);
        }

        and = new AndOperation(and, andBetweenRight);

        ExistsQuantification existsQuantification1 = new ExistsQuantification(and, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateRightSemijoin(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visit(NaturalJoin naturalJoin) { // TODO: Kontrola
        Pair<Header, Formula> leftSubtreeResult = this.visit(naturalJoin.leftExpression);
        Pair<Header, Formula> rightSubtreeResult = this.visit(naturalJoin.rightExpression);

        String freshVariable1 = this.freshVariableGenerator.getFreshVariable();
        String freshVariable2 = this.freshVariableGenerator.getFreshVariable();

        Set<String> leftSubtreeColumnsCopy = leftSubtreeResult.a.getColumnsCopy();
        Set<String> rightSubtreeColumnsCopy = rightSubtreeResult.a.getColumnsCopy();

        Set<String> commonColumns = new HashSet<>(leftSubtreeColumnsCopy);
        commonColumns.retainAll(rightSubtreeColumnsCopy);

        Set<String> attributesFromLeftWithoutCommon = new HashSet<>(leftSubtreeColumnsCopy);
        attributesFromLeftWithoutCommon.removeAll(commonColumns);

        Set<String> attributesFromRightWithoutCommon = new HashSet<>(rightSubtreeColumnsCopy);
        attributesFromRightWithoutCommon.removeAll(commonColumns);

        Formula andBetweenCommons1 = this.getAndFromColumns(new ArrayList<>(commonColumns), freshVariable1, freshVariable2);
        // There's a free choice whether to use freshVariable1 or freshVariable2 and it doesn't matter which one we choose
        Formula andBetweenCommons2 = this.getAndFromColumns(new ArrayList<>(commonColumns), this.TUPLE_VARIABLE_NAME, freshVariable1);

        Formula andBetweenLeftWithoutCommon = this.getAndFromColumns(new ArrayList<>(attributesFromLeftWithoutCommon), this.TUPLE_VARIABLE_NAME, freshVariable1);
        Formula andBetweenRightWithoutCommon = this.getAndFromColumns(new ArrayList<>(attributesFromRightWithoutCommon), this.TUPLE_VARIABLE_NAME, freshVariable2);

        Formula rewrittenLeftSubtreeResult = this.toTRCPredicateConverter.convert(leftSubtreeResult.b, freshVariable1);
        Formula rewrittenRightSubtreeResult = this.toTRCPredicateConverter.convert(rightSubtreeResult.b, freshVariable2);

        AndOperation joiningAnd = new AndOperation(rewrittenLeftSubtreeResult, rewrittenRightSubtreeResult);

        if (andBetweenCommons1 != null) {
            joiningAnd = new AndOperation(joiningAnd, andBetweenCommons1);
        }

        if (andBetweenCommons2 != null) {
            joiningAnd = new AndOperation(joiningAnd, andBetweenCommons2);
        }

        if (andBetweenLeftWithoutCommon != null) {
            joiningAnd = new AndOperation(joiningAnd, andBetweenLeftWithoutCommon);
        }

        if (andBetweenRightWithoutCommon != null) {
            joiningAnd = new AndOperation(joiningAnd, andBetweenRightWithoutCommon);
        }

        if (naturalJoin.thetaCondition.formula != null) {
            Formula andOperationForThetaCondition = this.toTRCPredicateConverter.visit(naturalJoin.thetaCondition.formula);
            joiningAnd = new AndOperation(joiningAnd, andOperationForThetaCondition);
        }

        ExistsQuantification existsQuantification1 = new ExistsQuantification(joiningAnd, freshVariable2);
        ExistsQuantification existsQuantification2 = new ExistsQuantification(existsQuantification1, freshVariable1);

        Header newHeader = HeaderEvaluator.evaluateNaturalJoin(leftSubtreeResult.a, rightSubtreeResult.a);

        return new Pair<>(newHeader, existsQuantification2);
    }

    public Pair<Header, Formula> visit(RightOuterJoin rightOuterJoin) {
        return null;
    }

    public Pair<Header, Formula> visit(Rename rename) {
        return null;
    }

    public Pair<Header, Formula> visit(LeftOuterJoin leftOuterJoin) {
        return null;
    }

    public Pair<Header, Formula> visit(FullOuterJoin fullOuterJoin) {
        return null;
    }

    public Pair<Header, Formula> visit(ThetaCondition thetaCondition) {
        return null;
    }
}
