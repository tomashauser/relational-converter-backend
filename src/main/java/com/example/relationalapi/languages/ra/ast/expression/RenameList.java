package com.example.relationalapi.languages.ra.ast.expression;

import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public class RenameList extends Expression {
    public final List<Pair<String, String>> renameList;

    public RenameList(List<Pair<String, String>> renameList) {
        this.renameList = renameList;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
