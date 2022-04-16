package com.example.relationalapi.languages.ra.visitors;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;
import com.example.relationalapi.languages.logicalexpression.ast.FormulaParentheses;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.AndOperation;
import com.example.relationalapi.languages.logicalexpression.ast.binarylogicaloperation.OrOperation;
import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.NumberTerm;
import com.example.relationalapi.languages.logicalexpression.ast.terms.ColumnSpecification;
import com.example.relationalapi.languages.logicalexpression.ast.terms.StringTerm;
import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomExpressionGenerator {
    private RandomExpressionGenerator() {
    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static String getRandomLetter(boolean capital) {
        char c = capital ? 'A' : 'a';
        c += getRandomNumber(0, 25);
        return String.valueOf(c);
    }

    private static RenameList getRandomRenameList(int length) {
        if (length == 0) {
            return new RenameList(new ArrayList<>());
        }

        char c = 'a';
        c += getRandomNumber(0, 25 - (length * 2));

        List<Pair<String, String>> pairs = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            char d = c;
            d += 1;

            pairs.add(new Pair<>(String.valueOf(c), String.valueOf(d)));
            c += 2;
        }

        return new RenameList(pairs);
    }

    public static String generateStandardStringExpr(int minLength, int maxLength) {
        int length = getRandomNumber(minLength, maxLength);

        return (new ToStandardNotationConverter()).visit(Objects.requireNonNull(generateExpr(length)));
    }

    public static String generateStandardStringExpr(int length) {
        return (new ToStandardNotationConverter()).visit(Objects.requireNonNull(generateExpr(length)));
    }

    public static String generateSimplifiedStringExpr(int length) {
        return (new ToSimplifiedNotationConverter()).visit(Objects.requireNonNull(generateExpr(length)));
    }

    public static Expression generateExpr(int length) {
        int n = length <= 0 ? 0 : getRandomNumber(0, 17);

        ThetaCondition thetaCondition = new ThetaCondition(null);

        if (n >= 9 && getRandomNumber(0, 1) == 0) { // 50 % chance for an empty theta condition
            thetaCondition = generateThetaCondition(getRandomNumber(1, 3));
        }

        ThetaCondition emptyThetaCondition = new ThetaCondition(null);

        switch(n) {
            case 0: return new Relation(getRandomLetter(true));
            case 1: return new ParenthesesExpression(generateExpr(length - 1));
            case 2: return new Projection(new ColumnList(Arrays.asList("a", "b")), generateExpr(length - 1));
            case 3: return new CartesianProduct(generateExpr(length - 1), generateExpr(length - 1));
            case 4: return new Difference(generateExpr(length - 1), generateExpr(length - 1));
            case 5: return new Union(generateExpr(length - 1), generateExpr(length - 1));
            case 6: return new Intersection(generateExpr(length - 1), generateExpr(length - 1));
            case 7: return new Division(generateExpr(length - 1), generateExpr(length - 1));
            case 8: return new Rename(getRandomRenameList(getRandomNumber(1, 3)), generateExpr(length - 1));
            case 9: return new Selection(thetaCondition, generateExpr(length - 1));
            case 10: return new FullOuterJoin(generateExpr(length - 1), generateExpr(length - 1), emptyThetaCondition);
            case 11: return new LeftAntijoin(generateExpr(length - 1), generateExpr(length - 1), emptyThetaCondition);
            case 12: return new LeftOuterJoin(generateExpr(length - 1), generateExpr(length - 1), emptyThetaCondition);
            case 13: return new LeftSemijoin(generateExpr(length - 1), generateExpr(length - 1), thetaCondition);
            case 14: return new NaturalJoin(generateExpr(length - 1), generateExpr(length - 1), thetaCondition);
            case 15: return new RightAntijoin(generateExpr(length - 1), generateExpr(length - 1), emptyThetaCondition);
            case 16: return new RightOuterJoin(generateExpr(length - 1), generateExpr(length - 1), emptyThetaCondition);
            case 17: return new RightSemijoin(generateExpr(length - 1), generateExpr(length - 1), thetaCondition);
            default: return null;
        }
    }

    private static ThetaCondition generateThetaCondition(int length) {
        return new ThetaCondition(generateFormula(length));
    }
    
    private static Formula generateFormula(int length) {
        int n = length <= 0 ? 0 : getRandomNumber(0, 3);
        
        switch (n) {
            case 0: return generateThetaConditionPredicate();
            case 1: return new AndOperation(generateFormula(length - 1), generateFormula(length - 1));
            case 2: return new OrOperation(generateFormula(length - 1), generateFormula(length - 1));
            case 3: return new FormulaParentheses(generateFormula(length - 1));
            default: return null;
        }
    }

    private static ComparisonPredicate generateThetaConditionPredicate() {
        int n = getRandomNumber(0, 4);

        ColumnSpecification columnSpecification = new ColumnSpecification(getRandomLetter(false));

        String symbol = "=";

        switch(n) {
            case 0: symbol = "="; break;
            case 1: symbol = "<"; break;
            case 2: symbol = ">"; break;
            case 3: symbol = "\\leq"; break;
            case 4: symbol = "\\geq"; break;
        }

        if (symbol.equals("=")) {
            if (getRandomNumber(0, 1) == 0) {
                return new ComparisonPredicate(columnSpecification, new StringTerm(getRandomLetter(getRandomNumber(0, 1) == 0)), symbol);
            } else {
                return new ComparisonPredicate(columnSpecification, new NumberTerm(getRandomNumber(0, 9)), symbol);
            }
        }

        return new ComparisonPredicate(columnSpecification, new NumberTerm(getRandomNumber(0, 9)), symbol);
    }
}
