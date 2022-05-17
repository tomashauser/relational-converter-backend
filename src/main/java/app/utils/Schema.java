package app.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schema {
    private HashMap<String, Header> schema = new HashMap<>();
    private String schemaInJson;

    public Schema(String schemaInJson) {
        this.schemaInJson = schemaInJson;

        try {
            JSONArray tables = new JSONArray(schemaInJson);

            for (int i = 0; i < tables.length(); i++) {
                JSONArray current = new JSONArray(tables.getString(i));

                String tableName = current.getString(0);
                JSONArray jsonColumns = new JSONArray(current.getString(1));

                List<String> stringColumns = new ArrayList<>();

                for (int j = 0; j < jsonColumns.length(); j++) {
                    stringColumns.add(jsonColumns.getString(j));
                }

                this.schema.put(tableName, new Header(stringColumns));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Schema(HashMap<String, Header> schema) {
        this.schema = schema;
    }

    public boolean isEmpty() {
        return this.schema.isEmpty();
    }

    public Header getTableHeaderByName(String name) {
        Header header = this.schema.get(name);

        return header;
    }

    public boolean columnExistsInTable(String tableName, String columnName) {
        return this.schema.get(tableName) != null && this.schema.get(tableName).getColumns().contains(columnName);
    }

    public boolean tableExists(String name) {
        return this.schema.containsKey(name);
    }

    public Schema getCopy() {
        return new Schema(this.schemaInJson);
    }
}
