package app.languages.ra.utils;

import java.util.List;
import java.util.function.Function;

public class FreshVariableGenerator {
    private Integer index = 0;

    // First few variables before resorting to numbering
    private final List<String> defaultVars;

    // Function that takes an index and returns a numbered variable
    private final Function<Integer, String> getNumberedVariable;

    public FreshVariableGenerator(List<String> defaultVars, Function<Integer, String> getNumberedVariable) {
        this.defaultVars = defaultVars;
        this.getNumberedVariable = getNumberedVariable;
    }

    public String getFreshVariable() {
        if (this.index < this.defaultVars.size()) {
            String ret = this.defaultVars.get(this.index++);

            return ret;
        }

        return this.getNumberedVariable.apply(this.index++ - this.defaultVars.size());
    }
}
