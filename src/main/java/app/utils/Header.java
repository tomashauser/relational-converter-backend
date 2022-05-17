package app.utils;

import java.util.*;

public class Header {
    private final Set<String> columns;

    private final List<String> listData;

    public Header(Set<String> columns) {
        this.columns = columns;
        this.listData = new ArrayList<>(this.columns);
    }

    public Header(List<String> columns) {
        this.columns = new HashSet<>(columns);
        this.listData = columns;

        if (this.columns.size() < columns.size()) {
            throw new IllegalArgumentException();
        }
    }

    public Set<String> getColumns() {
        return this.columns;
    }

    public String getColumnsString() {
        return this.columns.toString().replace("[", "{").replace("]", "}");
    }

    public List<String> getListData() {
        return this.listData;
    }

    public Set<String> getColumnsCopy() {
        return new HashSet<>(this.columns);
    }

    public boolean contains(String columnName) {
        return this.columns.contains(columnName);
    }
}
