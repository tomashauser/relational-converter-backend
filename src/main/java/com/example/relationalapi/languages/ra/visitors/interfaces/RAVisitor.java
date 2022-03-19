package com.example.relationalapi.languages.ra.visitors.interfaces;

import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;

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
