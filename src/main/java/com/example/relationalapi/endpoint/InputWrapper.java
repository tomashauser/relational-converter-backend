package com.example.relationalapi.endpoint;

import com.example.relationalapi.utils.Schema;
import org.json.JSONException;
import org.json.JSONObject;

public class InputWrapper {
    private final String expression;
    private final Schema schema;
    private final boolean semanticChecking;
    private final boolean prenexForm;

    public InputWrapper(String input) throws IllegalArgumentException, JSONException {
        JSONObject jsonObject = new JSONObject(input);

        this.expression = jsonObject.getString("expression").replace("$", "");

        String schemaString = jsonObject.getString("schema");

        this.schema = new Schema(schemaString);

        JSONObject options = jsonObject.getJSONObject("options");
        this.semanticChecking = this.parseBoolWithException("semanticChecking", options);
        this.prenexForm = this.parseBoolWithException("prenexForm", options);
    }

    public InputWrapper(String expression, Schema schema, boolean semanticChecking, boolean prenexForm) {
        this.expression = expression;
        this.schema = schema;
        this.semanticChecking = semanticChecking;
        this.prenexForm = prenexForm;
    }

    private boolean parseBoolWithException(String propertyName, JSONObject jsonObject) throws JSONException {
        String str = jsonObject.getString(propertyName);

        if (str == null || (!str.equals("true") && !str.equals("false"))) {
            throw new IllegalArgumentException("Error when parsing " + str + " value of " + propertyName);
        }

        return Boolean.parseBoolean(str);
    }

    public String getExpression() {
        return expression;
    }

    public Schema getSchema() {
        return schema;
    }

    public boolean isSemanticChecking() {
        return semanticChecking;
    }

    public boolean isPrenexForm() {
        return prenexForm;
    }
}
