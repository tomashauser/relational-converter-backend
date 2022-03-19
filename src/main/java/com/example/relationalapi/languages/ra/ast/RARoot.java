package com.example.relationalapi.languages.ra.ast;

import com.example.relationalapi.languages.ra.ast.expression.Expression;

public class RARoot {
    public final Expression expression;

    public RARoot(Expression expression) {
        this.expression = expression;
    }
}
