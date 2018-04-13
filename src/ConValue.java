import java.util.Objects;

public class ConValue implements Comparable<ConValue> {
    public static final ConValue VOID = null;

    private Object value;
    private Type type;

    public ConValue(Object value, Type type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public Integer asInteger() {
        if (!type.equals(Type.INTEGER)) throw new RuntimeException(value + " cannot be converted to Integer");

        return (Integer) value;
    }

    public String asString() {
        if (!type.equals(Type.STRING)) throw new RuntimeException(value + " cannot be converted to String");

        return (String) value;
    }

    public Boolean asBoolean() {
        if (!type.equals(Type.BOOLEAN)) throw new RuntimeException(value + " cannot be converted to Boolean");

        return (Boolean) value;
    }

    @Override
    public int compareTo(ConValue that) {
        if (!this.type.equals(that.type)) {
            throw new RuntimeException("not comparable types: " + this.type + " and " + that.type);
        }

        switch (this.type) {
            case INTEGER:
                return ((Integer) this.value).compareTo((Integer) that.value);
            case STRING:
                return ((String) this.value).compareTo((String) that.value);
            default:
                throw new RuntimeException("not comparable type: " + type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConValue conValue = (ConValue) o;
        return Objects.equals(type, conValue.type) &&
                Objects.equals(value, conValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
