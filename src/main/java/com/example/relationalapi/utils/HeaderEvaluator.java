package com.example.relationalapi.utils;

import com.example.relationalapi.languages.ra.ast.expression.Projection;
import com.example.relationalapi.languages.ra.ast.expression.Rename;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class HeaderEvaluator {
    private static Header getUnion(Header left, Header right) {
        Set<String> leftColumnNames = left.getColumnsCopy();
        Set<String> rightColumnNames = right.getColumnsCopy();

        leftColumnNames.addAll(rightColumnNames);

        return new Header(leftColumnNames);
    }

    public static Header evaluateProjection(Projection projection, Header header) {
        Set<String> columnNames = header.getColumnsCopy();
        List<String> projectionColumnNames = projection.columnList.columnNames;

        columnNames.removeIf(columnName -> !projectionColumnNames.contains(columnName));

        return new Header(columnNames);
    }

    public static Header evaluateRename(Rename rename, Header header) {
        List<String> currentColumns = header.getListData();
        List<Pair<String, String>> renameList = rename.renameList.renameList;

        HashMap<String, String> renamingMap = new HashMap<>();

        for (Pair<String, String> renaming : renameList) {
            renamingMap.put(renaming.a, renaming.b);
        }

        for (int i = 0; i < currentColumns.size(); i++) {
            String newColumn = renamingMap.get(currentColumns.get(i));

            if (newColumn != null) {
                currentColumns.set(i, newColumn);
            }
        }

        return new Header(currentColumns);
    }
    
    public static Header evaluateCartesianProduct(Header left, Header right) {
        return HeaderEvaluator.getUnion(left, right);
    }

    public static Header evaluateDivision(Header left, Header right) {
        Set<String> leftColumnNames = left.getColumnsCopy();
        Set<String> rightColumnNames = right.getColumnsCopy();

        // Perform intersection
        Set<String> intersection = new HashSet<>(leftColumnNames);
        intersection.retainAll(rightColumnNames);

        // Perform set difference
        leftColumnNames.removeAll(intersection);

        return new Header(leftColumnNames);
    }

    public static Header evaluateLeftAntijoin(Header left, Header right) {
        return right;
    }

    public static Header evaluateRightAntijoin(Header left, Header right) {
        return left;
    }

    public static Header evaluateLeftSemijoin(Header left, Header right) {
        return left;
    }

    public static Header evaluateRightSemijoin(Header left, Header right) {
        return right;
    }

    public static Header evaluateNaturalJoin(Header left, Header right) {
        return HeaderEvaluator.getUnion(left, right);
    }

    public static Header evaluateLeftOuterJoin(Header left, Header right) {
        return HeaderEvaluator.getUnion(left, right);
    }

    public static Header evaluateRightOuterJoin(Header left, Header right) {
        return HeaderEvaluator.getUnion(left, right);
    }

    public static Header evaluateFullOuterJoin(Header left, Header right) {
        return HeaderEvaluator.getUnion(left, right);
    }
}
