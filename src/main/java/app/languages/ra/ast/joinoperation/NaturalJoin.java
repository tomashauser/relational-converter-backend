package app.languages.ra.ast.joinoperation;

import app.languages.ra.visitors.interfaces.RAVisitor;
import app.languages.ra.ast.expression.Expression;
import app.languages.ra.ast.expression.ThetaCondition;

public class NaturalJoin extends JoinOperation {
    public NaturalJoin(Expression leftExpression, Expression rightExpression, ThetaCondition thetaCondition) {
        super(leftExpression, rightExpression, thetaCondition);
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor) {
        return raVisitor.visit(this);
    }
}
