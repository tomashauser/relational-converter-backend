package app.languages.ra.ast.expression;

import app.languages.ra.visitors.interfaces.RAVisitor;

import java.util.List;

public class ColumnList extends Expression {
    public final List<String> columnNames;

    public ColumnList(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public <T> T accept(RAVisitor<T> raVisitor)  {
        return raVisitor.visit(this);
    }
}
