package com.example.relationalapi.languages.ra.ast.expression;

import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;

public abstract class Expression {
   public abstract <T> T accept(RAVisitor<T> raVisitor);
}
