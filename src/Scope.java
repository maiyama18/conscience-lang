import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Scope parent;
    private Map<String, Integer> variables;

    Scope(Scope parent) {
        this.parent = parent;
        variables = new HashMap<>();
    }

    Scope() {
        this(null);
    }

    public void assign(String var, Integer value) {
        variables.put(var, value);
    }

    public void mutate(String var, Integer value) {
        if (resolve(var) == null) {
            System.out.println("undefined variable: " + var);
        }

        if (variables.containsKey(var)) {
            variables.put(var, value);
        } else {
            if (parent == null) {
                System.out.println("undefined variable: " + var);
            } else {
                parent.mutate(var, value);
            }
        }
    }

    public Integer resolve(String var) {
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
