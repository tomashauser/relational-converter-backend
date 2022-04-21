package app.languages.ra.visitors;

import app.languages.logicalexpression.ast.Formula;
import app.languages.logicalexpression.visitors.SemanticThetaConditionChecker;
import app.languages.ra.ast.binaryoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import app.languages.ra.visitors.interfaces.RAVisitor;
import app.languages.ra.ast.binaryoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;
import app.utils.Header;
import app.utils.HeaderEvaluator;
import app.utils.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class SemanticRAChecker implements RAVisitor<Header> {
    private final Schema schema;

    private List<String> errors = new ArrayList<>();
    private SemanticThetaConditionChecker semanticThetaConditionChecker = new SemanticThetaConditionChecker();

    public SemanticRAChecker(Schema schema) {
        this.schema = schema;
    }

    public String check(Expression expression) {
        this.errors = new ArrayList<>();
        this.visit(expression);
        return String.join("", this.errors);
    }

    private boolean areUnionCompatible(Header header1, Header header2) {
        Set<String> header1Columns = header1.getColumns();
        Set<String> header2Columns = header2.getColumns();

        return header1Columns.size() == header2Columns.size() && header1Columns.containsAll(header2Columns);
    }

    private boolean haveDisjointAttributes(Header header1, Header header2) {
        Set<String> intersection = header1.getColumnsCopy();
        intersection.retainAll(header2.getColumns());

        return intersection.isEmpty();
    }

    private void resolveThetaJoin(JoinOperation joinOperation, Header left, Header right) {
        Formula formula = joinOperation.thetaCondition.formula;

        if (formula != null && !this.haveDisjointAttributes(left, right)) {
            this.errors.add("Theta join requires disjoint attributes.");
        }

        if (formula != null) {
            this.errors.addAll(this.semanticThetaConditionChecker.getErrorsForThetaJoin(formula, left, right));
        }
    }

    private Header visitJoin(BiFunction<Header, Header, Header> action, JoinOperation joinOperation) {
        Header left = this.visit(joinOperation.leftExpression);
        Header right = this.visit(joinOperation.rightExpression);

        this.resolveThetaJoin(joinOperation, left, right);

        return action.apply(left, right);
    }

    public Header visit(Expression expression) {
        return expression.accept(this);
    }

    // Both relations must have disjoint attributes
    public Header visit(CartesianProduct cartesianProduct) {
        Header left = this.visit(cartesianProduct.leftExpr);
        Header right = this.visit(cartesianProduct.rightExpr);

        if (!this.haveDisjointAttributes(left, right)) {
            this.errors.add("Cartesian product requires disjoint attributes.");
        }

        return HeaderEvaluator.evaluateCartesianProduct(left, right);
    }

    // Union-compatibility
    public Header visit(Difference difference) {
        Header left = this.visit(difference.leftExpr);
        Header right = this.visit(difference.rightExpr);

        if (!this.areUnionCompatible(left, right)) {
            this.errors.add("Set difference requires union-compatible relations.");
        }

        return left;
    }

    // Union-compatibility
    public Header visit(Intersection intersection) {
        Header left = this.visit(intersection.leftExpr);
        Header right = this.visit(intersection.rightExpr);

        if (!this.areUnionCompatible(left, right)) {
            this.errors.add("Intersection requires union-compatible relations.");
        }

        return left;
    }

    // Union-compatibility
    public Header visit(Union union) {
        Header left = this.visit(union.leftExpr);
        Header right = this.visit(union.rightExpr);

        if (!this.areUnionCompatible(left, right)) {
            this.errors.add("Union requires union-compatible relations.");
        }

        return left;
    }

    // Right is a proper subset to left
    public Header visit(Division division) {
        Header left = this.visit(division.leftExpr);
        Header right = this.visit(division.rightExpr);

        Set<String> leftColumnNames = left.getColumnsCopy();
        Set<String> rightColumnNames = right.getColumnsCopy();

        if (!leftColumnNames.containsAll(rightColumnNames) || leftColumnNames.equals(rightColumnNames)) {
            this.errors.add("Division requires that a right relation is a proper subset of the left one.");
        }

        return HeaderEvaluator.evaluateDivision(left, right);
    }

    public Header visit(ParenthesesExpression parenthesesExpression) {
        return this.visit(parenthesesExpression.expression);
    }

    // Projecting columns are in the inner expression
    public Header visit(Projection projection) {
        Header subtreeResult = this.visit(projection.expression);

        if (!subtreeResult.getColumns().containsAll(projection.columnList.columnNames)) {
            this.errors.add("You can only project on attributes that are in the inner expression.");
        }

        return HeaderEvaluator.evaluateProjection(projection, subtreeResult);
    }

    // Relation must be in the schema
    public Header visit(Relation relation) {
        Header header = this.schema.getTableHeaderByName(relation.name);

        if (header == null) {
            this.errors.add("Relation " + relation.name + " isn't in the schema.");
            return new Header(new ArrayList<>());
        }

        return header;
    }

    public Header visit(Selection selection) {
        Header header = this.visit(selection.expression);

        this.errors.addAll(this.semanticThetaConditionChecker.getErrorsForSelection(selection.thetaCondition.formula, header));

        return header;
    }

    public Header visit(LeftSemijoin leftSemijoin) {
        return this.visitJoin(HeaderEvaluator::evaluateLeftSemijoin, leftSemijoin);
    }

    // Disjoint attributes when theta condition is present
    public Header visit(NaturalJoin naturalJoin) {
        return this.visitJoin(HeaderEvaluator::evaluateNaturalJoin, naturalJoin);
    }

    public Header visit(RightSemijoin rightSemijoin) {
        return this.visitJoin(HeaderEvaluator::evaluateRightSemijoin, rightSemijoin);
    }

    public Header visit(LeftAntijoin leftAntijoin) {
        return this.visitJoin(HeaderEvaluator::evaluateLeftAntijoin, leftAntijoin);
    }

    public Header visit(RightAntijoin rightAntijoin) {
        return this.visitJoin(HeaderEvaluator::evaluateRightAntijoin, rightAntijoin);
    }

    public Header visit(Rename rename) {
        Header header = this.visit(rename.expression);

        List<String> baseColumns = header.getListData();

        rename.renameList.renameList.forEach(r -> {
            if (!baseColumns.contains(r.a)) {
                this.errors.add("Column " + r.a + " is not in the inner expression in renaming.");
            }
        });

        try {
            return HeaderEvaluator.evaluateRename(rename, header);
        } catch (IllegalArgumentException e) {
            this.errors.add("Renaming creates duplicit columns.");
        }

        return header;
    }

    public Header visit(LeftOuterJoin leftOuterJoin) {
        return this.visitJoin(HeaderEvaluator::evaluateLeftOuterJoin, leftOuterJoin);
    }

    public Header visit(RightOuterJoin rightOuterJoin) {
        return this.visitJoin(HeaderEvaluator::evaluateRightOuterJoin, rightOuterJoin);
    }

    public Header visit(FullOuterJoin fullOuterJoin) {
        return this.visitJoin(HeaderEvaluator::evaluateFullOuterJoin, fullOuterJoin);
    }

    public Header visit(ThetaCondition thetaCondition) {
        return null;
    }
}
