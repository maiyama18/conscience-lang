import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Scope parent;
    private Map<String, ConValue> variables;

    Scope(Scope parent) {
        this.parent = parent;
        variables = new HashMap<>();
    }

    Scope() {
        this(null);
    }

    public void assign(String var, ConValue value) {
        variables.put(var, value);
    }

    public void mutate(String var, ConValue value) {
        if (resolve(var) == null) throw new RuntimeException("undefined variable: " + var);

        if (variables.containsKey(var)) {
            variables.put(var, value);
        } else {
            if (parent == null) throw new RuntimeException("undefined variable: " + var);

            parent.mutate(var, value);
        }
    }

    public ConValue resolve(String var) {
        if (variables.containsKey(var)) {
            return variables.get(var);
        } else {
            if (parent == null) return null;
            else return parent.resolve(var);
        }
    }

    public Scope getParent() {
        return parent;
    }
}
