package app.languages.ra.visitors.interfaces;

import app.languages.ra.ast.binaryoperation.*;
import app.languages.ra.ast.expression.*;
import app.languages.ra.ast.joinoperation.*;

public interface RAVisitor<T> {
    T visit(Expression expression) ;

    T visit(CartesianProduct cartesianProduct) ;
    T visit(Difference difference);
    T visit(Division division);
    T visit(Intersection intersection);
    T visit(Union union);
    T visit(ThetaCondition thetaCondition);
    T visit(ParenthesesExpression parenthesesExpression);
    T visit(Projection projection) ;
    T visit(Relation relation) ;
    T visit(Selection selection) ;
    T visit(FullOuterJoin fullOuterJoin);
    T visit(LeftAntijoin leftAntijoin) ;
    T visit(LeftSemijoin leftSemijoin);
    T visit(LeftOuterJoin leftOuterJoin);
    T visit(NaturalJoin naturalJoin);
    T visit(RightAntijoin rightAntijoin) ;
    T visit(RightOuterJoin rightOuterJoin);
    T visit(RightSemijoin rightSemijoin);
    T visit(Rename rename);
}
